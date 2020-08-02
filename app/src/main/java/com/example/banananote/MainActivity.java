package com.example.banananote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.View.OnClickListener; //클릭 이벤트
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity { //implements OnClickListener
    //상속 OnClick 으로 쓰려다가 일단 햄버거 버튼 하나라서 바로 클릭 이벤트 사용함.

    private static final int REQ_CODE_OVERLAY_PERMISSION = 1;
    Intent foregroundServiceIntent;
    private static final int MESSAGE_PERMISSION_GRANTED = 1111;
    private static final int MESSAGE_PERMISSION_DENIED = 1112;

    //menu 부분
    private DisplayMetrics metrics; //매트릭스로 디스플레이 크기 측정
    private LinearLayout MenuPanel; //메뉴 내용물이 보일 창
    private LinearLayout MainPanel; //매인 화면
    private FrameLayout.LayoutParams MainPanelParameters; //매인화면 파라미터
    private FrameLayout.LayoutParams MenuPanelParameters; //메뉴화면 파라미터
    private int PanelWidth; //디스플레이 크기 값 저장
    private boolean is_Panel_Expanded; //패널 움직임 여부 (메뉴가 아닌 매인 패널이 움직인다)
    //is_Panel_Expanded 를  static 으로 했으나 플로팅버튼에 의해 값이 저장되므로 없앰.
    private Button btn_menu; //메뉴가 보여지게 할 햄버거 버튼

    //페이저
    ViewPager pager;

    private View Frag_Main;

    Switch Btn_Permission; //플로팅 버튼 허가 해지

    static int Stop = 0;

    //하단 탭
    private TabLayout tabLayout;

    //activity_bottom_menu.xml
    LinearLayout Linear_ALL;
    LinearLayout Linear_Favorites;
    LinearLayout Linear_Tag;
    LinearLayout Linear_Lock;

    //pager position value
    int Position;

    ///
    public static Context context_main;
    public static Boolean Edit_Activation; //체크박스 활성화

    String a;
    public static String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tag = "multi";
        tag = "single";
        ///
        context_main = MainActivity.this;
        Edit_Activation = false; //기본값 비활성화
        //finish();
        //openView();
        /*if(Build.VERSION.SDK_INT >= 26) {
            openView1();
        }
        else openView();*/

        //Light 모드
        //getWindow().setStatusBarColor(Color.parseColor("#fff9eb"));

        //허가 버튼
        Btn_Permission = findViewById(R.id.switch_Permission);

        /*//새로운 시도
        if (Settings.canDrawOverlays(MainActivity.this)) {
            if (FloatingViewService.serviceIntent == null) {
                foregroundServiceIntent = new Intent(MainActivity.this, FloatingViewService.class);
                startService(foregroundServiceIntent);
            } else {
                foregroundServiceIntent = FloatingViewService.serviceIntent;
            }
        } else
            onObtainingPermissionOverlayWindow();*/

        /*//새로운 시도
        if (Settings.canDrawOverlays(MainActivity.this)) {
            if (FloatingViewService.serviceIntent == null) {
                Btn_Permission.setChecked(true);
                foregroundServiceIntent = new Intent(MainActivity.this, FloatingViewService.class);
                startService(foregroundServiceIntent);
            } else {
                Btn_Permission.setChecked(false);
                foregroundServiceIntent = FloatingViewService.serviceIntent;
            }
        } else
            Btn_Permission.setChecked(false);*/

        if (FloatingViewService.isService) Btn_Permission.setChecked(true);
        else Btn_Permission.setChecked(false);

        Btn_Permission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //새로운 시도
                    if (Settings.canDrawOverlays(MainActivity.this)) {
                        foregroundServiceIntent = new Intent(MainActivity.this, FloatingViewService.class);
                        startService(foregroundServiceIntent);
                    } else {
                        new AwesomeSuccessDialog(MainActivity.this)
                                .setTitle("빠른 메모")
                                .setMessage("빠른 메모를 위해 권한이 필요합니다")
                                .setCancelable(true)
                                .setPositiveButtonText("허용")
                                .setNegativeButtonText("취소")
                                .setPositiveButtonClick(new Closure() {
                                    @Override
                                    public void exec() {
                                        //새로운 시도
                                        if (Settings.canDrawOverlays(MainActivity.this)) {
                                            foregroundServiceIntent = new Intent(MainActivity.this, FloatingViewService.class);
                                            startService(foregroundServiceIntent);
                                        } else {
                                            onObtainingPermissionOverlayWindow();
                                        }
                                    }
                                })
                                .setNegativeButtonClick(new Closure() {
                                    @Override
                                    public void exec() {
                                        Btn_Permission.setChecked(false);
                                    }
                                }).show();
                        //onObtainingPermissionOverlayWindow();
                    }


                } else {
                    foregroundServiceIntent = new Intent(MainActivity.this, FloatingViewService.class);
                    stopService(foregroundServiceIntent);
                    Stop = 1;
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        //toolBarLayout.setTitle(getTitle()); //프로젝트명
        toolBarLayout.setTitle("ALL");
        getWindow().setStatusBarColor(Color.parseColor("#fff9eb"));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        //FloatingActionButton fab (Add Memo)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "메모추가 인텐트로 이동", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NoteAddActivity.class);
                startActivity(intent);
            }
        });


        //bottom CustomBottomNavigationView
        /*CustomBottomNavigationView customBottomNavigationView =
                findViewById(R.id.customBottomBar);
        customBottomNavigationView.inflateMenu(R.menu.menu_bottom);*/

        //bottom TabLayout code num = 1
        /*tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("전체"));
        tabLayout.addTab(tabLayout.newTab().setText("즐겨찾기"));
        tabLayout.addTab(tabLayout.newTab().setText("폴더"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/


        //페이저 기능
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(4); //main,favorites,tag,lock

        Tab_PagerAdapter adapter = new Tab_PagerAdapter(getSupportFragmentManager());

        Fragment_Main fragment_main = new Fragment_Main();
        adapter.addItem(fragment_main);

        Fragment_Favorites fragment_favorites = new Fragment_Favorites();
        adapter.addItem(fragment_favorites);

        Fragment_Tag fragment_tag = new Fragment_Tag();
        adapter.addItem(fragment_tag);

        Fragment_Lock fragment_lock = new Fragment_Lock();
        adapter.addItem(fragment_lock);

        pager.setPageTransformer(true, new DepthPageTransformer());
        pager.setAdapter(adapter);


        //Bottom TabLayout with ViewPager code num = 1
        /*pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        Linear_ALL = findViewById(R.id.Linear_ALL);
        Linear_Favorites = findViewById(R.id.Linear_Favorites);
        Linear_Tag = findViewById(R.id.Linear_Tag);
        Linear_Lock = findViewById(R.id.Linear_Lock);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        Position = position;
                        toolBarLayout.setTitle("ALL");
                        //Toast.makeText(MainActivity.this,"페이지 1",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Position = position;
                        toolBarLayout.setTitle("Favorites");
                        //Toast.makeText(MainActivity.this, "페이지 2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Position = position;
                        toolBarLayout.setTitle("Tag");
                        //Toast.makeText(MainActivity.this, "페이지 3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Position = position;
                        toolBarLayout.setTitle("Lock");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.Linear_ALL:
                        pager.setCurrentItem(0, false);
                        //tag = "single";
                        //restart();
                        break;
                    case R.id.Linear_Favorites:
                        pager.setCurrentItem(1,false);
                        //tag = "multi";
                        //restart();

                        break;
                    case R.id.Linear_Tag:
                        pager.setCurrentItem(2, false);
                        //fragment_main.selectedClick();
                        break;
                    case R.id.Linear_Lock:
                        pager.setCurrentItem(3, false);
                        break;
                }
            }
        };

        Linear_ALL.setOnClickListener(onClickListener);
        Linear_Favorites.setOnClickListener(onClickListener);
        Linear_Tag.setOnClickListener(onClickListener);
        Linear_Lock.setOnClickListener(onClickListener);


        Frag_Main = getLayoutInflater().inflate(R.layout.fragment_main, null, false);


        metrics = new DisplayMetrics(); //메트릭스 객체
        getWindowManager().getDefaultDisplay().getMetrics(metrics); //디스플레이 값 저장
        PanelWidth = (int) ((metrics.widthPixels) * 0.85); //메뉴가 적당히 보일때까지 밀려진 길이 측정

        MainPanel = (LinearLayout) findViewById(R.id.MainPanel);
        MainPanelParameters = (FrameLayout.LayoutParams) MainPanel.getLayoutParams(); //xml 에서 정의한 값 가져옴
        MainPanelParameters.width = metrics.widthPixels; //가져온 값에서 width 값 재정의
        MainPanel.setLayoutParams(MainPanelParameters); //재정의한 값 재적용

        MenuPanel = (LinearLayout) findViewById(R.id.MenuPanel);
        MenuPanelParameters = (FrameLayout.LayoutParams) MenuPanel.getLayoutParams(); //위와 동일
        MenuPanelParameters.width = PanelWidth;

        MenuPanel.setLayoutParams(MenuPanelParameters);

        //메뉴 패널을 touch 이벤트로 움직이게 한다(일단 보류)
        /*MenuPanel.setOnTouchListener(new View.OnTouchListener() {
			@Override

			public boolean onTouch(View view, MotionEvent motionEvent) {

				//float X = slidingPanel.getX();
				float dx = slidingPanel.getX() - motionEvent.getRawX();
				//float Y = slidingPanel.getY();

				switch (motionEvent.getAction()) {
					case MotionEvent.ACTION_DOWN:
						//X = slidingPanel.getX();
						dx = slidingPanel.getX() - motionEvent.getRawX();
						Log.e("test", "손가락 눌림 : " + dx + ",");
						return true;
					case MotionEvent.ACTION_MOVE:

						slidingPanel.setX(motionEvent.getRawX());
						Log.e("test", "손가락 움직임 : ");
						return true;
					case MotionEvent.ACTION_UP:
						Log.e("test", "손가락 뗌 : " + dx + ",");
						if (slidingPanel.getX() < metrics.widthPixels * 0.45) {
							//menuLeftSlideAnimationToggle();
							slidingPanel.animate()
									.x(0.0f)
									.setDuration(800)
									.start();
							//isLeftExpanded = false;
						} else if (slidingPanel.getX() > metrics.widthPixels * 0.45)
							//isLeftExpanded = true;
							//menuLeftSlideAnimationToggle();
							slidingPanel.animate()
									.x(panelWidth)
									.setDuration(800)
									.start();
						return false;

					default:
						return false;
				}
				//return false;

			}
		});*/

        //메뉴 버튼 일단 비활성화
        //btn_menu.setOnClickListener(this);
        //btn_menu = (Button) findViewById(R.id.btn_Menu);
        /*btn_menu.setOnClickListener(new Button.OnClickListener() { //메뉴가 열리고 닫힘 (나중에 클릭 많아지면 상속받아서 온클릭 switch 로 뺄 수도 있다.)
            @Override
            public void onClick(View view) {

                if (!is_Panel_Expanded) {
                    is_Panel_Expanded = true; //else로 넘기기 위해
                    MainPanel.animate()
                            .x(PanelWidth)
                            .setDuration(800)
                            .start();

                    //매인 레이아웃 안보이게
                    //버튼을 누르면 레이아웃을 안눌리게 처리한다
                    //옆으로 밀려난 매인 화면은 무조건 Empty 레이아웃이 감싸고 그 값만 터치 이벤트로 (empty 화면은 투명 레이ㅏ아웃)
                    //다시 원래 화면으로 돌아올 수 있다.

                    //FrameLayout ViewGroup = (FrameLayout) findViewById(R.id.Frame_RelativeLayout).getParent(); //레이아웃 정보 가져옴
                    androidx.coordinatorlayout.widget.CoordinatorLayout ViewGroup =
                            (androidx.coordinatorlayout.widget.CoordinatorLayout) findViewById(R.id.pager).getParent();
                    ViewGroup_Enable_Toggle(ViewGroup, false);
                    findViewById(R.id.btn_Menu).setEnabled(false); //따로 있는 버튼도 안보여야한다.(이 부분은 액션바로 대체할 예정)

                    //투명 레이아웃 보이개
                    ((LinearLayout) findViewById(R.id.Frame_Empty_LinearLayout)).setVisibility(View.VISIBLE);
                    findViewById(R.id.Frame_Empty_LinearLayout).setEnabled(true); //활성화
                    findViewById(R.id.Frame_Empty_LinearLayout).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            MainPanel.animate()
                                    .x(0)
                                    .setDuration(800)
                                    .start();
                            is_Panel_Expanded = false;

                            //빈 레이아웃을 터치 함으로써 다시 원상태로 돌아옴 빈 레이아웃은 다시 비활성화
                            //FrameLayout ViewGroup = (FrameLayout) findViewById(R.id.Frame_RelativeLayout).getParent();
                            androidx.coordinatorlayout.widget.CoordinatorLayout ViewGroup =
                                    (androidx.coordinatorlayout.widget.CoordinatorLayout) findViewById(R.id.pager).getParent();
                            ViewGroup_Enable_Toggle(ViewGroup, true);
                            findViewById(R.id.btn_Menu).setEnabled(true); //따로 있는 버튼도 안보여야한다.(이 부분은 액션바로 대체할 예정)

                            ((LinearLayout) findViewById(R.id.Frame_Empty_LinearLayout)).setVisibility(View.GONE);
                            findViewById(R.id.Frame_Empty_LinearLayout).setEnabled(false);

                            return true;
                        }
                    });
                } else {
                    MainPanel.animate()
                            .x(0)
                            .setDuration(800)
                            .start();
                }
            }
        });*/


        View main_cardview;
        main_cardview = getLayoutInflater().inflate(R.layout.frag_main_item, null,false);

        CheckBox main_checkBox;
        main_checkBox = main_cardview.findViewById(R.id.main_checkbox);

        main_checkBox.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        if (!Settings.canDrawOverlays(MainActivity.this)) {
            if (FloatingViewService.isService) Btn_Permission.setChecked(true);
            else Btn_Permission.setChecked(false);
        }
        super.onStop();
    }


    //child 뷰 들이 활성화 비활성화 한다.
    public static void ViewGroup_Enable_Toggle(ViewGroup viewGroup, boolean Enable) {
        int ChildActivity_Count = viewGroup.getChildCount();
        for (int i = 0; i < ChildActivity_Count; i++) {
            View view = viewGroup.getChildAt(i);

            if (view.getId() != android.R.id.home) { //R.id.btn_Menu
                view.setEnabled(Enable);
                if (view instanceof ViewGroup) {
                    ViewGroup_Enable_Toggle((ViewGroup) view, Enable);
                }
            }
        }
    }
    //매인액티비티 종료되면 같이 종료된다.

   /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        if(foregroundServiceIntent !=null) {
            stopService(foregroundServiceIntent);
            foregroundServiceIntent = null;
        }
    }
*/

    public void openView() {
        if (Settings.canDrawOverlays(this)) //api 23
            startService(new Intent(this, FloatingViewService.class));
        else
            onObtainingPermissionOverlayWindow();
    }

    public void openView1() {
        if (Settings.canDrawOverlays(this)) //api 23
            startForegroundService(new Intent(this, FloatingViewService.class)); //startService //android 8 이상부터 변경
        else
            onObtainingPermissionOverlayWindow();
    }

    //권한
    public void onObtainingPermissionOverlayWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION);
    }

    //페이저 기능
    class Tab_PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public Tab_PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    //ActionBar menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);*/
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("검색");

        searchView.setOnClickListener(new SearchView.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //글씨 들어가면 mainActivity 에서 메모지 불러옴.
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                return true;
            }
        });
        return true;
    }

    //ActionBar menu Selected


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //Left Hamburger Button
                if (!is_Panel_Expanded) {
                    is_Panel_Expanded = true; //else로 넘기기 위해
                    MainPanel.animate()
                            .x(PanelWidth)
                            .setDuration(300)
                            .start();

                    //매인 레이아웃 안보이게
                    //버튼을 누르면 레이아웃을 안눌리게 처리한다
                    //옆으로 밀려난 매인 화면은 무조건 Empty 레이아웃이 감싸고 그 값만 터치 이벤트로 (empty 화면은 투명 레이ㅏ아웃)
                    //다시 원래 화면으로 돌아올 수 있다.

                    //FrameLayout ViewGroup = (FrameLayout) findViewById(R.id.Frame_RelativeLayout).getParent(); //레이아웃 정보 가져옴
                    androidx.coordinatorlayout.widget.CoordinatorLayout ViewGroup =
                            (androidx.coordinatorlayout.widget.CoordinatorLayout) findViewById(R.id.pager).getParent();
                    ViewGroup_Enable_Toggle(ViewGroup, false);
                    //findViewById(R.id.btn_Menu).setEnabled(false); //따로 있는 버튼도 안보여야한다.(이 부분은 액션바로 대체할 예정)

                    //투명 레이아웃 보이개
                    ((LinearLayout) findViewById(R.id.Frame_Empty_LinearLayout)).setVisibility(View.VISIBLE);
                    findViewById(R.id.Frame_Empty_LinearLayout).setEnabled(true); //활성화
                    findViewById(R.id.Frame_Empty_LinearLayout).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            MainPanel.animate()
                                    .x(0)
                                    .setDuration(300)
                                    .start();
                            is_Panel_Expanded = false;

                            //빈 레이아웃을 터치 함으로써 다시 원상태로 돌아옴 빈 레이아웃은 다시 비활성화
                            //FrameLayout ViewGroup = (FrameLayout) findViewById(R.id.Frame_RelativeLayout).getParent();
                            androidx.coordinatorlayout.widget.CoordinatorLayout ViewGroup =
                                    (androidx.coordinatorlayout.widget.CoordinatorLayout) findViewById(R.id.pager).getParent();
                            ViewGroup_Enable_Toggle(ViewGroup, true);
                            //findViewById(R.id.btn_Menu).setEnabled(true); //따로 있는 버튼도 안보여야한다.(이 부분은 액션바로 대체할 예정)

                            ((LinearLayout) findViewById(R.id.Frame_Empty_LinearLayout)).setVisibility(View.GONE);
                            findViewById(R.id.Frame_Empty_LinearLayout).setEnabled(false);

                            return true;
                        }
                    });
                } else {
                    MainPanel.animate()
                            .x(0)
                            .setDuration(300)
                            .start();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void restart() {

        /*Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);*/

        Edit_Activation = true;

        //페이저 기능

        Tab_PagerAdapter adapter = new Tab_PagerAdapter(getSupportFragmentManager());

        Fragment_Main fragment_main = new Fragment_Main();
        adapter.addItem(fragment_main);

        Fragment_Favorites fragment_favorites = new Fragment_Favorites();
        adapter.addItem(fragment_favorites);

        Fragment_Tag fragment_tag = new Fragment_Tag();
        adapter.addItem(fragment_tag);

        Fragment_Lock fragment_lock = new Fragment_Lock();
        adapter.addItem(fragment_lock);

        pager.setPageTransformer(true, new DepthPageTransformer());
        pager.setAdapter(adapter);

        Linear_ALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "testestestsetests", Toast.LENGTH_SHORT).show();
                fragment_main.selectedClick();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Edit_Activation = false;
        //페이저 기능

        Tab_PagerAdapter adapter = new Tab_PagerAdapter(getSupportFragmentManager());

        Fragment_Main fragment_main = new Fragment_Main();
        adapter.addItem(fragment_main);

        Fragment_Favorites fragment_favorites = new Fragment_Favorites();
        adapter.addItem(fragment_favorites);

        Fragment_Tag fragment_tag = new Fragment_Tag();
        adapter.addItem(fragment_tag);

        Fragment_Lock fragment_lock = new Fragment_Lock();
        adapter.addItem(fragment_lock);

        pager.setPageTransformer(true, new DepthPageTransformer());
        pager.setAdapter(adapter);

        //super.onBackPressed();
    }
}
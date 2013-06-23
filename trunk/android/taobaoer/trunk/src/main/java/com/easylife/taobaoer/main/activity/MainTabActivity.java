package com.easylife.taobaoer.main.activity;

import java.util.Timer;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.widget.tab.TabBar;
import com.easylife.taobaoer.core.widget.tab.TabBarItem;
import com.easylife.taobaoer.home.activity.CategoryActivity;
import com.easylife.taobaoer.home.activity.IndexActivity;
import com.easylife.taobaoer.home.activity.SettingActivity;
import com.easylife.taobaoer.home.activity.TopicActivity;

public class MainTabActivity extends ActivityGroup {

	/**
	 * tab
	 */
	public static TabBar<Intent> tabBar;

	private Timer timer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater layoutInflater = getLayoutInflater();
		int itemIndex = getIntent().getIntExtra("itemIndex", -1);
		tabBar = new TabBar<Intent>(R.layout.page_main_tab,
				MainTabActivity.this);
		tabBar.addTabBarItem(new TabBarItem<Intent>(R.drawable.poster_nav_home,
				R.drawable.poster_nav_home_press, R.string.tabitem_index,
				new Intent(this, IndexActivity.class)));
		tabBar.addTabBarItem(new TabBarItem<Intent>(
				R.drawable.poster_nav_classification,
				R.drawable.poster_nav_classification_press,
				R.string.tabitem_category, new Intent(this,
						CategoryActivity.class)));
		tabBar.addTabBarItem(new TabBarItem<Intent>(
				R.drawable.poster_nav_topic, R.drawable.poster_nav_topic_press,
				R.string.tabitem_topic, new Intent(this, TopicActivity.class)));
		tabBar.addTabBarItem(new TabBarItem<Intent>(R.drawable.poster_nav_me,
				R.drawable.poster_nav_me_press, R.string.tabitem_mine,
				new Intent(this, SettingActivity.class)));
		tabBar.setBgResources(R.drawable.tab_bar_background);
		setContentView(tabBar.build(layoutInflater, this, itemIndex));
	}

}
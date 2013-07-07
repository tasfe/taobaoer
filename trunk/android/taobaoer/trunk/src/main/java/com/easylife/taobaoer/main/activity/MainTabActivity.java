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
import com.easylife.taobaoer.home.activity.FavActivity;
import com.easylife.taobaoer.home.activity.IndexActivity;

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
		tabBar.addTabBarItem(new TabBarItem<Intent>(R.drawable.home_link,
				R.drawable.home_hover, R.string.tabitem_index, new Intent(this,
						IndexActivity.class)));
		tabBar.addTabBarItem(new TabBarItem<Intent>(R.drawable.all_link,
				R.drawable.all_hover, R.string.tabitem_category, new Intent(
						this, CategoryActivity.class)));
		tabBar.addTabBarItem(new TabBarItem<Intent>(R.drawable.fav_link,
				R.drawable.fav_hover, R.string.tabitem_topic, new Intent(this,
						FavActivity.class)));
		tabBar.setBgResources(R.drawable.tab_bar_background);
		setContentView(tabBar.build(layoutInflater, this, itemIndex));
	}

}
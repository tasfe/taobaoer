package com.easylife.taobaoer.core.task;

import org.springframework.util.StringUtils;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.utils.DialogUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ProgressTask extends AsyncTask<Void, Void, String> {
	protected Context context;
	private ProgressDialog progressDialog;
	private TaskCallback callback;
	private boolean defaultStyle = true;

	public ProgressTask(Context context, TaskCallback callback) {
		super();
		this.context = context;
		this.callback = callback;
	}

	public ProgressTask(Context context, TaskCallback callback,
			boolean defaultStyle) {
		super();
		this.context = context;
		this.callback = callback;
		this.defaultStyle = defaultStyle;
	}

	@Override
	protected String doInBackground(Void... params) {
		if (null != callback) {
			return callback.doInBackground();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String errorInfo) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		if (StringUtils.hasText(errorInfo)) {
			DialogUtils.showErrorDialog(context, errorInfo);
			// DialogUtils.showToastText(context, errorInfo);
		} else {
			if (defaultStyle) {
				// DialogUtils.showToastText(context, R.string.success);
				DialogUtils.showSuccessDialog(context, R.string.success);
			}
			if (callback != null) {
				callback.successCallback();
			}
		}
	}

	@Override
	protected void onPreExecute() {
		if (progressDialog != null) {
			progressDialog.show();
		} else {
			progressDialog = ProgressDialog.show(context, context
					.getResources().getString(R.string.sending), context
					.getResources().getString(R.string.please_wait), true,
					false);
		}
		super.onPreExecute();
	}
}

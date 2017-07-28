package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.text.Spannable;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.utils.EaseSmileUtils;

/**
 * Created by yeqinfu on 2017/7/3. 新增系统通知消息，客服转发提示语
 */

public class EaseChatRowSystemMsg extends EaseChatRow {
	private LinearLayout	contentView;
	private TextView		tv_msg;

	public EaseChatRowSystemMsg(Context context, EMMessage message, int position, BaseAdapter adapter) {
		super(context, message, position, adapter);
	}

	@Override
	protected void onInflatView() {
		inflater.inflate(R.layout.ease_row_system_msg, this);
	}

	@Override
	protected void onFindViewById() {
		contentView = (LinearLayout) findViewById(R.id.ll_content);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

	}

	@Override
	protected void onUpdateView() {
        adapter.notifyDataSetChanged();
	}

	@Override
	protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
        // 设置内容
        tv_msg.setText(span, TextView.BufferType.SPANNABLE);
	}

	@Override
	protected void onBubbleClick() {

	}
}

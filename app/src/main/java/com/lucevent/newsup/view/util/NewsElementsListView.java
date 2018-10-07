package com.lucevent.newsup.view.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.Zoomy;
import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.SDManager;
import com.lucevent.newsup.net.ConnectivityManager;
import com.lucevent.newsup.parse.NewsBlockquote;
import com.lucevent.newsup.parse.NewsElement;
import com.lucevent.newsup.parse.NewsElements;
import com.lucevent.newsup.parse.NewsTitle;
import com.lucevent.newsup.view.adapter.viewholder.news.NewsBlockquoteViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.news.NewsElementViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder;

import java.util.ArrayList;

public class NewsElementsListView extends ScrollView {

	private final ArrayList<NewsElementViewHolder> mViews;

	private static int mTextSize;
	private static boolean mDarkStyle;
	private static Site mCurrentSite;

	private final String TWITTER_SCRIPT, INSTAGRAM_SCRIPT;

	private final LinearLayout mContainer;

	private LayoutInflater mInflater;
	private ConnectivityManager mConnectivityManager;
	private Activity mActivityContext;
	private View.OnLongClickListener mOnImageViewLongClickListener;

	private ContentLoader contentLoader;

	public NewsElementsListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mActivityContext = (Activity) context;

		mContainer = new LinearLayout(context);
		mContainer.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		mContainer.setOrientation(LinearLayout.VERTICAL);
		addView(mContainer);

		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mConnectivityManager = new ConnectivityManager(context);

		mViews = new ArrayList<>(16);
		contentLoader = new ContentLoader(mViews);

		TWITTER_SCRIPT = "<script async defer>" + SDManager.readRaw(context, R.raw.twitter) + "</script>";
		INSTAGRAM_SCRIPT = "<script>" + SDManager.readRaw(context, R.raw.instagram) + "</script>";

		mTextSize = AppSettings.getFontSize(2);
		mDarkStyle = AppSettings.getNightModeStatus();
	}

	public void set(String newsTitle, NewsElements elements, Site site)
	{
		mCurrentSite = site;
		contentLoader.reset(this, newsTitle, elements, computeLinkColor(mCurrentSite));
	}

	public void clear()
	{
		mViews.clear();
		contentLoader.clear();
		mContainer.removeAllViews();
	}

	public void setDarkStyle(boolean darkStyle)
	{
		mDarkStyle = darkStyle;
		int linkColor = mCurrentSite != null ? computeLinkColor(mCurrentSite) : 0xff3333ff;
		for (NewsElementViewHolder h : mViews) {
			h.setStyle(mDarkStyle);
			h.setLinkColor(linkColor);
		}
	}

	public void setTextSize(int textSize)
	{
		mTextSize = textSize;
		for (NewsElementViewHolder h : mViews) {
			h.setTextSize(mTextSize);
		}
	}

	public void setImageLongClickListener(OnLongClickListener listener)
	{
		mOnImageViewLongClickListener = listener;
	}

	private NewsElementViewHolder createViewHolder(NewsElement elem)
	{
		NewsElementViewHolder h;
		switch (elem.getType()) {
			case NewsElement.TYPE_TITLE:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder(mInflater.inflate(R.layout.i_news_title, this, false), NewsTextViewHolder.TEXT_SIZE_TITLE);
				break;
			case NewsElement.TYPE_SUBTITLE:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder(mInflater.inflate(R.layout.i_news_subtitle, this, false), NewsTextViewHolder.TEXT_SIZE_LARGE);
				break;
			case NewsElement.TYPE_PARAGRAPH:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder(mInflater.inflate(R.layout.i_news_paragraph, this, false), NewsTextViewHolder.TEXT_SIZE_NORMAL);
				break;
			case NewsElement.TYPE_IMAGE:
				Zoomy.Builder zoomyBuilder = new Zoomy.Builder(mActivityContext)
						.enableImmersiveMode(false)
						.longPressListener(new LongPressListener() {
							@Override
							public void onLongPress(View v)
							{
								mOnImageViewLongClickListener.onLongClick(v);
							}
						});
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsImageViewHolder(mInflater.inflate(R.layout.i_news_image, this, false), zoomyBuilder);
				break;
			case NewsElement.TYPE_CAPTION:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder(mInflater.inflate(R.layout.i_news_caption, this, false), NewsTextViewHolder.TEXT_SIZE_SMALL);
				break;
			case NewsElement.TYPE_BLOCKQUOTE:
				NewsBlockquote nbq = (NewsBlockquote) elem;
				NewsBlockquoteViewHolder bqvh = new NewsBlockquoteViewHolder(mInflater.inflate(R.layout.i_news_blockquote, this, false), nbq.size());
				for (NewsElement ne : nbq)
					bqvh.addElement(createViewHolder(ne));
				h = bqvh;
				break;
			case NewsElement.TYPE_LIST_ITEM:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsListItemViewHolder(mInflater.inflate(R.layout.i_news_list_item, this, false));
				break;
			case NewsElement.TYPE_TWEET:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTweetViewHolder(mInflater.inflate(R.layout.i_news_webview, this, false), TWITTER_SCRIPT);
				break;
			case NewsElement.TYPE_INSTAGRAM:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsInstagramViewHolder(mInflater.inflate(R.layout.i_news_webview, this, false), INSTAGRAM_SCRIPT);
				break;
			case NewsElement.TYPE_DL_TITLE:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder(mInflater.inflate(R.layout.i_news_dl_title, this, false), NewsTextViewHolder.TEXT_SIZE_NORMAL);
				break;
			case NewsElement.TYPE_DL_DESCRIPTION:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTextViewHolder(mInflater.inflate(R.layout.i_news_dl_description, this, false), NewsTextViewHolder.TEXT_SIZE_NORMAL);
				break;
			case NewsElement.TYPE_TABLE:
				h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsTableViewHolder(mInflater.inflate(R.layout.i_news_table, this, false));
				break;
			default:
				if (mConnectivityManager.isInternetAvailable())
					switch (elem.getType()) {
						default:
						case NewsElement.TYPE_IFRAME:
							h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsIframeViewHolder(mInflater.inflate(R.layout.i_news_webview, this, false));
							break;
						case NewsElement.TYPE_VIDEO:
							h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsVideoViewHolder(mInflater.inflate(R.layout.i_news_webview, this, false));
							break;
						case NewsElement.TYPE_AUDIO:
							h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsAudioViewHolder(mInflater.inflate(R.layout.i_news_webview, this, false));
							break;
						case NewsElement.TYPE_WIDGET:
							h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsNUWidgetViewHolder(mInflater.inflate(R.layout.i_news_webview, this, false));
					}
				else
					h = new com.lucevent.newsup.view.adapter.viewholder.news.NewsNIMediaViewHolder(mInflater.inflate(R.layout.i_news_blockquote, this, false), mInflater);
		}
		h.setNewsElement(elem);
		return h;
	}

	private int computeLinkColor(Site site)
	{
		int c;
		if (mDarkStyle) {
			if (site.getColorDarkness() > 0.95)
				c = 0xff555555;
			else if (site.getColorDarkness() > 0.7)
				c = Utils.brighter(site.color, 1.2 - site.getColorDarkness());
			else
				c = site.color;
		} else
			c = site.getColorDarkness() < 0.3 ? Utils.darker(site.color, 0.6) : site.color;

		return c;
	}

	class ContentLoader implements ViewTreeObserver.OnScrollChangedListener {

		private static final int BOTTOM_OFFSET = 600;
		private static final int BOUND_VIEWS_AT_START = 10;

		private int mLinkColor;
		private ScrollView mScrollView;
		private final ArrayList<NewsElementViewHolder> mViews;
		private NewsElements mElements;

		private int lastPositionBound;

		ContentLoader(ArrayList<NewsElementViewHolder> views)
		{
			mViews = views;
		}

		void reset(ScrollView scrollView, String title, NewsElements elements, int linkColor)
		{
			mScrollView = scrollView;
			mElements = elements;
			mLinkColor = linkColor;

			//
			NewsTitle nt = new NewsTitle();
			nt.setContent(title);
			addElementView(nt);

			lastPositionBound = Math.min(mElements.size(), BOUND_VIEWS_AT_START) - 1;
			for (int i = 0; i <= lastPositionBound; i++)
				addElementView(mElements.get(i));

			if (mElements.size() - 1 > lastPositionBound)
				mScrollView.getViewTreeObserver().addOnScrollChangedListener(this);
		}

		private void addElementView(NewsElement elem)
		{
			NewsElementViewHolder holder = createViewHolder(elem);
			mViews.add(holder);
			mContainer.addView(holder.get());

			holder.bind(mDarkStyle);
			holder.setTextSize(mTextSize);
			holder.setStyle(mDarkStyle);
			holder.setLinkColor(mLinkColor);
		}

		public void clear()
		{
			if (mElements != null)
				mElements.clear();
		}

		@Override
		public void onScrollChanged()
		{
			if (lastPositionBound + 1 >= mElements.size()) {
				mScrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
				return;
			}

			int visibleBottom = mScrollView.getScrollY() + mScrollView.getHeight();
			int boundBottom = mViews.get(mViews.size() - 1).get().getBottom();

			if (visibleBottom + BOTTOM_OFFSET > boundBottom) {
				lastPositionBound++;

				addElementView(mElements.get(lastPositionBound));

				if (lastPositionBound + 1 >= mElements.size())
					mScrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
			}
		}

	}

}

package co.dporn.gmd.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import co.dporn.gmd.client.presenters.AppPresenter;
import co.dporn.gmd.client.presenters.AppPresenter.AppLayoutView;
import co.dporn.gmd.client.presenters.ContentPresenter;
import co.dporn.gmd.client.presenters.ContentPresenter.ContentView;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialSideNavPush;

public class AppLayoutUi extends Composite implements AppLayoutView {
	
	@UiField
    protected MaterialSideNavPush sidenav;
	
	@UiField
	protected MaterialPanel panel;
	
	private ContentView container;
	
	private static AppLayoutUiUiBinder uiBinder = GWT.create(AppLayoutUiUiBinder.class);

	interface AppLayoutUiUiBinder extends UiBinder<Widget, AppLayoutUi> {
	}

	public AppLayoutUi() {
		initWidget(uiBinder.createAndBindUi(this));
//		container.clear();
	}

	@Override
	public void bindPresenter(AppPresenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbindPresenter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onLoad() {
		super.onLoad();
	}

	@Override
	public void replaceContentPresenter(ContentPresenter childPresenter) {
		GWT.log(this.getClass().getSimpleName()+"#replaceContentPresenter: "+childPresenter.getClass().getSimpleName());
		ContentView view = childPresenter.getContentView();
		if (container!=null) {
			panel.remove(container.asWidget());
			GWT.log("remove");
		} 
		Scheduler.get().scheduleDeferred(()->{
			int sidenavix = panel.getWidgetIndex(sidenav);
			GWT.log("sidenavix: "+sidenavix);
			panel.insert(view.asWidget(), sidenavix+1);
			GWT.log("insert");
			container=view;
		});
	}
}

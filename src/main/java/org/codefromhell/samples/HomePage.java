package org.codefromhell.samples;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

import java.io.File;
import java.net.URI;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    private String status = "before click";
    
    public HomePage(final PageParameters parameters) {
        final Form form = new Form("form", new CompoundPropertyModel<Object> (this));
        form.setOutputMarkupId(true);
        add(form);
        form.add(new Label("status"));

        final AjaxDownloadBehavior ajaxDownloadBehavior = new AjaxDownloadBehavior() {
            @Override
            protected String getFileName() {
                return "image.jpg";
            }

            @Override
            protected IResourceStream getResourceStream() {
                File imageFile = new File(getClass().getResource("/someImage.jpg").getFile());
                return new FileResourceStream(imageFile);
            }
        };
        form.add(ajaxDownloadBehavior);

        AjaxLink link = new AjaxLink("link") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                status = "after click";
                ajaxDownloadBehavior.initiate(target);
                target.add(form);
            }
        };
        form.add(link);

    }
}

package hudson.plugins.chucknorris;

import hudson.Extension;
import hudson.Util;
import hudson.model.AbstractProject;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.util.FormValidation;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.export.Exported;

public class ChuckProjectProperty extends JobProperty<AbstractProject<?,?>> {

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static final class DescriptorImpl extends JobPropertyDescriptor {

        private boolean optIn = false;
    	
        public DescriptorImpl() {
            super(ChuckProjectProperty.class);
            load();
        }
        
        @Override
        public boolean isApplicable(Class<? extends Job> jobType) {
            return false;
        }
        
        public String getDisplayName() {
            return "Chuck Norris Plugin";
        }
        
        @Override
        public ChuckProjectProperty newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return new ChuckProjectProperty();
        }
        
        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) {
            optIn = req.getParameter("chucknorris.optIn") != null;
            save();
            return true;
        }

        
        public boolean getOptIn() {
            return optIn;
        }
    }
}

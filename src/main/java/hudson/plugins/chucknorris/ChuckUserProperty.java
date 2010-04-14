package hudson.plugins.chucknorris;

import hudson.Extension;
import hudson.ExtensionPoint;
import hudson.model.Describable;
import hudson.model.Hudson;
import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

/**
 * 
 * @author Andrew Bayer
 */

@ExportedBean(defaultVisibility = 999)
public class ChuckUserProperty extends UserProperty implements Describable<UserProperty>, ExtensionPoint {

    private boolean showChuckOptIn = false;
    private boolean showChuckOptOut = false;
    
    @DataBoundConstructor
    public ChuckUserProperty(Boolean showChuckOptIn, Boolean showChuckOptOut) {
        this.showChuckOptIn=Boolean.TRUE.equals(showChuckOptIn);
        this.showChuckOptOut=Boolean.TRUE.equals(showChuckOptOut);
    }

    @Exported
    public User getUser() {
        return user;
    }

    @Exported
    public boolean getShowChuckOptIn() {
        return showChuckOptIn;
    }

    @Exported
    public boolean getShowChuckOptOut() {
        return showChuckOptOut;
    }

    @Exported
    public boolean getShouldShowChuck() {
        // If true, users won't see Chuck unless they opt in, so return showChuckOptIn.
        if (getGlobalChuckOptIn()) {
            return showChuckOptIn;
        }
        // If false, users will see Chuck unless they opt out.
        // So return the inverse of showChuckOptOut.
        else {
            return !showChuckOptOut;
        }
    }
        
    @Exported
    public boolean getGlobalChuckOptIn() {
        return ChuckProjectProperty.DESCRIPTOR.getOptIn();
    }
    

    @Extension
    public static final class ChuckUserPropertyDescriptor extends UserPropertyDescriptor {
        public ChuckUserPropertyDescriptor() {
            super(ChuckUserProperty.class);
        }

        @Override
        public String getDisplayName() {
            return "Chuck Norris Plugin";
        }

        @Override
        public ChuckUserProperty newInstance(StaplerRequest req, JSONObject formData)
            throws hudson.model.Descriptor.FormException {

            return req.bindJSON(ChuckUserProperty.class, formData);
        }

        @Override
        public UserProperty newInstance(User arg0) {
            return null;
        }
    }
}
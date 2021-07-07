package pl.sunflux.sandbox.ui.components;

public class ControlButton {
    public final int id;
    public final String title;
    public final boolean isActive;
    public final boolean isDisabled;
    public final String shortcut;

    public ControlButton(int id, String title, boolean isActive, boolean isDisabled, String shortcut) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.isDisabled = isDisabled;
        this.shortcut = shortcut;
    }

    public String getCssClass() {
        if (isActive) {
            return "active";
        }

        if (isDisabled) {
            return "disabled";
        }

        return "";
    }
}

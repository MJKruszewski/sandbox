package pl.sunflux.sandbox.ui.views.menu;

import pl.sunflux.sandbox.state.ApplicationSession;
import pl.sunflux.sandbox.ui.components.ControlButton;
import pl.sunflux.sandbox.ui.views.Templates;
import pl.sunflux.sandbox.ui.views.ViewInterface;

public class StartMenuView implements ViewInterface {

    private final static StartMenuView self = new StartMenuView();

    public static StartMenuView getInstance() {
        return self;
    }

    public String getMainContent() {
        return Templates.getTemplate(Templates.TemplatesEnum.START_MENU).execute(new Object() {
            String AUTHOR = ApplicationSession.AUTHOR;
            String GAME_NAME = ApplicationSession.GAME_NAME;
        });

//        return "<h1 class='special-text' style='font-size:48px; line-height:52px; text-align:center;'>" + ApplicationSession.GAME_NAME + "</h1>"
//                + "<h5 class='special-text' style='text-align:center;'>Created by " + ApplicationSession.AUTHOR + "</h5>"
//                + "<br/>"
//                + "<p>"
//                + "This game is a text-based RPG, and contains a lot of graphics. You must agree to the game's disclaimer before playing this game!"
//                + "</p>"
//                + "<p>"
//                + "You can visit my blog to check on development progress (use the 'Blog' button below to open the blog in your default browser)."
//                + "</p>"
//                + "<p style='text-align:center'>"
//                + "<b>Please use either my blog or github to get the latest official version of Lilith's Throne!</b>"
//                + "</p>";
    }

    public String getButtonMenu() {
        return Templates.getTemplate(Templates.TemplatesEnum.BUTTON_MENU).execute(new Object() {
            ControlButton[] buttons = new ControlButton[]{
                    new ControlButton(1, "Start", false, false, "Alt + 1"),
                    new ControlButton(2, "Load", false, false, ""),
                    new ControlButton(3, "Options", false, false, ""),
                    new ControlButton(4, "", false, true, ""),
                    new ControlButton(5, "", false, true, ""),

                    new ControlButton(6, "", false, true, ""),
                    new ControlButton(7, "", false, true, ""),
                    new ControlButton(8, "", false, true, ""),
                    new ControlButton(9, "", false, true, ""),
                    new ControlButton(10, "", false, true, ""),

                    new ControlButton(11, "Github", false, false, ""),
                    new ControlButton(12, "", false, true, ""),
                    new ControlButton(13, "", false, true, ""),
                    new ControlButton(14, "", false, true, ""),
                    new ControlButton(15, "", false, true, ""),
            };
        });
    }
}

package pl.sunflux.sandbox.ui.views;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import pl.sunflux.sandbox.state.ApplicationSession;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Templates {

    public enum TemplatesEnum {
        START_MENU,
        BUTTON_MENU
    }

    private static Map<TemplatesEnum, Template> enumStringMap = new HashMap<>(10);

    static {
        enumStringMap.put(TemplatesEnum.START_MENU, Mustache.compiler().escapeHTML(false).compile(new BufferedReader(new InputStreamReader(ApplicationSession.classLoader.getResourceAsStream("ui/html/start-menu.mustache")))));
        enumStringMap.put(TemplatesEnum.BUTTON_MENU, Mustache.compiler().escapeHTML(false).compile(new BufferedReader(new InputStreamReader(ApplicationSession.classLoader.getResourceAsStream("ui/html/button-menu.mustache")))));

        ApplicationSession.logger.debug("Templates loaded");
    }

    public static Template getTemplate(TemplatesEnum templatesEnum) {
        return enumStringMap.get(templatesEnum);
    }


}

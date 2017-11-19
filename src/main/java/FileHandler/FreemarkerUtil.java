package FileHandler;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {

    public String replace(String body, HashMap<String, String> mapWithData)
            throws TemplateException, IOException {
        Configuration cfg = new Configuration();
        StringWriter output = new StringWriter();
        Template tmpl = new Template(Long.valueOf(System.currentTimeMillis())
                .toString(), new StringReader(body), null);
        tmpl.process(mapWithData, output);
        return output.toString();
    }
}

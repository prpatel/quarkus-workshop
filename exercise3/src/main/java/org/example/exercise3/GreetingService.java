package org.example.exercise3;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GreetingService {

    private List<String> greetingsInMultipleLangs = new ArrayList<String>() {{
        add("Hello");
        add("Salut");
        add("Hola");
        add("Nǐ hǎo");
    }};

    public List<String> getAll() {
        return greetingsInMultipleLangs;
    }

    public String add(String name) {
        greetingsInMultipleLangs.add(name);
        return name;
    }

    public String get(int index) {
        return greetingsInMultipleLangs.get(index);
    }

    public void delete(int index) {
        greetingsInMultipleLangs.remove(index);
    }

    public String update(int index, String greeting) {

        greetingsInMultipleLangs.set(index, greeting);
        return greeting;
    }
}

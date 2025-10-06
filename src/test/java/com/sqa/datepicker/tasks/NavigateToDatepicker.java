package com.sqa.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToDatepicker implements Task {

    private final String url;

    public NavigateToDatepicker(String url) {
        this.url = url;
    }

    public static NavigateToDatepicker page() {
        return instrumented(NavigateToDatepicker.class, "https://jqueryui.com/datepicker/");
    }

    public static NavigateToDatepicker at(String url) {
        return instrumented(NavigateToDatepicker.class, url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Open.url(url)
        );
    }
}
package com.arcbees.testchosen.client.application.home;

import javax.inject.Inject;

import com.arcbees.chosen.client.ChosenOptions;
import com.arcbees.chosen.client.gwt.MultipleChosenValueListBox;
import com.google.common.collect.Lists;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    //First we declare our choices in an enum
    private enum Choices {
        VADER("Darth Vader"),
        LUKE("Luke Skywalker"),
        LEIA("Princess Leia"),
        BOBA_FETT("Boba Fett");

        public String getLiteral() {
            return name;
        }

        private final String name;

        Choices(String s) {
            name = s;
        }
    }

    //We decide how we want to show the different choices
    private static class ChoiceRenderer extends AbstractRenderer<Choices> {
        @Override
        public String render(Choices choices) {
            return choices != null ? choices.getLiteral() : "";
        }
    }

    //Don’t forget that the component has to be provided
    @UiField(provided = true)
    MultipleChosenValueListBox<Choices> multipleValues;

    @Inject
    HomeView(Binder uiBinder) {
        //There are multiple options that you can set on GWTChosen.
        //These and the construction have to be called before the call to createAndBindUi
        ChosenOptions chosenOptions = new ChosenOptions();
        chosenOptions.setPlaceholderText("Choose your favorite character");

        multipleValues = new MultipleChosenValueListBox<>(new ChoiceRenderer(), chosenOptions);

        initWidget(uiBinder.createAndBindUi(this));

        //Acceptable and selected values are set after createAndBindUi
        multipleValues.setAcceptableValues(Lists.newArrayList(Choices.values()));
        multipleValues.setValue(Lists.newArrayList(Choices.VADER, Choices.LEIA));
    }
}

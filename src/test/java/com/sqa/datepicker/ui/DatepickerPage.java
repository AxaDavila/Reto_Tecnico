package com.sqa.datepicker.ui;

import net.serenitybdd.screenplay.targets.Target;

public class DatepickerPage {
    
    // El iframe parece ser el problema - la página actual no lo tiene
    public static final Target DATEPICKER_IFRAME = Target.the("iframe")
            .locatedBy("//iframe");
    
    // NUEVO: Localizadores más específicos basados en la página real
    public static final Target DATE_INPUT_FIELD = Target.the("campo de fecha")
            .locatedBy("//input[contains(@class,'hasDatepicker')] | //input[@id='datepicker'] | //input[contains(@placeholder,'date')]");
    
    // Alternativa: buscar por el campo que realmente contiene la fecha
    public static final Target DATE_INPUT_FIELD_VISIBLE = Target.the("campo fecha visible")
            .locatedBy("//input[@type='text' and contains(@value,'2025')] | //input[contains(@value,'/'))]");
    
    public static final Target CALENDAR_WIDGET = Target.the("calendario")
            .locatedBy("//div[@id='ui-datepicker-div'] | //div[contains(@class,'ui-datepicker')]");
    
    public static final Target NEXT_MONTH_BUTTON = Target.the("próximo mes")
            .locatedBy("//span[@class='ui-icon ui-icon-circle-triangle-e'] | //a[contains(@class,'ui-datepicker-next')]");
    
    public static final Target CALENDAR_DAY = Target.the("día {0}")
            .locatedBy("//a[@class='ui-state-default' and text()='{0}'] | //td//a[text()='{0}']");
    
    public static final Target CURRENT_MONTH_DAY = Target.the("día {0} mes actual")
            .locatedBy("//td[@data-handler='selectDay']/a[text()='{0}'] | //a[contains(@class,'ui-state-default') and text()='{0}']");
            
    public static final Target CALENDAR_TABLE = Target.the("tabla calendario")
            .locatedBy("//table[@class='ui-datepicker-calendar'] | //table[contains(@class,'ui-datepicker')]");
}

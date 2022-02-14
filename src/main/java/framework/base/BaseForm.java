package framework.base;

import framework.utils.LoggerUtility;

public abstract class BaseForm {
    private BaseElement uniqueElement;
    protected String name;

    public BaseForm(BaseElement uniqueElement, String name) {
        this.uniqueElement = uniqueElement;
        this.name = name;
    }

    public boolean isUniqueElementDisplayed() {
        LoggerUtility.info("Checking for a unique form element: " + name);
        return this.uniqueElement.isDisplayed();
    }


}

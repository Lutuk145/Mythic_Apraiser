package me.lutuk.Screens;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;

public class ColorSetter extends Screen {
    public static CheckboxWidget checkBoxOne;
    protected ColorSetter() {
        super(Text.of("ColorSetter"));
    }

    @Override
    protected void init() {
        super.init();

        this.addDrawableChild(checkBoxOne =  new CheckboxWidget(this.width-10, this.height-10,60,60,Text.of("Test"),false));
    }
}

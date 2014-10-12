package com.teammetallurgy.atum.gui;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.utils.Constants;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class AtumConfigGui extends GuiConfig {

    static Configuration cfg = AtumConfig.config;
    static AtumConfig cfgh;

    public AtumConfigGui(GuiScreen parent) {
        super(parent, generateConfigList(), Constants.MODID, false, false, GuiConfig.getAbridgedConfigPath(AtumConfig.config.toString()));
    }

    public static List<IConfigElement> generateConfigList() {

        ArrayList<IConfigElement> elements = new ArrayList<IConfigElement>();

        elements.add(new ConfigElement(cfg.getCategory(cfgh.general)));
        System.out.println(cfgh.general);
        return elements;
    }
}

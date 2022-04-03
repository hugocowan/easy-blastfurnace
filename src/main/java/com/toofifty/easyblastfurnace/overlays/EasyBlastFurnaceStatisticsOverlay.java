package com.toofifty.easyblastfurnace.overlays;

import com.toofifty.easyblastfurnace.EasyBlastFurnaceConfig;
import com.toofifty.easyblastfurnace.EasyBlastFurnacePlugin;
import com.toofifty.easyblastfurnace.utils.SessionStatistics;
import net.runelite.api.MenuAction;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;

@Singleton
public class EasyBlastFurnaceStatisticsOverlay extends OverlayPanel
{
    @Inject
    private SessionStatistics statistics;

    @Inject
    private EasyBlastFurnaceConfig config;

    private final EasyBlastFurnacePlugin plugin;

    @Inject
    EasyBlastFurnaceStatisticsOverlay(EasyBlastFurnacePlugin plugin)
    {
        super(plugin);
        this.plugin = plugin;

        getMenuEntries().add(new OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OverlayManager.OPTION_CONFIGURE, "Easy blast furnace statistics"));
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!plugin.isEnabled()) return null;
        if (!config.showStatisticsOverlay()) return null;

        panelComponent.getChildren().add(LineComponent.builder()
            .left("Bars todo:")
            .right(Integer.toString(statistics.getTotalActionsBanked()))
            .build());

        panelComponent.getChildren().add(LineComponent.builder()
            .left("Bars made:")
            .right(Integer.toString(statistics.getTotalActionsDone()))
            .build());

        panelComponent.getChildren().add(LineComponent.builder()
            .left("XP banked:")
            .right(Double.toString(statistics.getTotalXpBanked()))
            .build());

        panelComponent.getChildren().add(LineComponent.builder()
            .left("XP gained:")
            .right(Double.toString(statistics.getTotalXpGained()))
            .build());

        panelComponent.getChildren().add(LineComponent.builder()
            .left("Stamina doses:")
            .right(Integer.toString(statistics.getStaminaDoses()))
            .build());

        return super.render(graphics);
    }
}

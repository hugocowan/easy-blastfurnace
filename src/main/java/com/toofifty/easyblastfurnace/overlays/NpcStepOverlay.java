package com.toofifty.easyblastfurnace.overlays;

import com.toofifty.easyblastfurnace.EasyBlastFurnaceConfig;
import com.toofifty.easyblastfurnace.config.HighlightOverlayTextSetting;
import com.toofifty.easyblastfurnace.steps.MethodStep;
import com.toofifty.easyblastfurnace.steps.NpcStep;
import com.toofifty.easyblastfurnace.steps.WidgetStep;
import com.toofifty.easyblastfurnace.utils.MethodHandler;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.WorldView;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.TextComponent;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;

@Singleton
public class NpcStepOverlay extends Overlay
{
    @Inject
    private Client client;

    @Inject
    private EasyBlastFurnaceConfig config;

    @Inject
    private MethodHandler methodHandler;

    NpcStepOverlay()
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ALWAYS_ON_TOP);
        setPriority(100F);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        MethodStep[] steps = methodHandler.getSteps();

        if (steps == null) return null;

        for (MethodStep step : steps) {
            if (!(step instanceof NpcStep)) continue;

			WorldView top = client.getTopLevelWorldView();
			if (top == null)
			{
				return null;
			}

			for (NPC npc : top.npcs())
			{
				if (npc == null) continue;
				if (npc.getId() != ((NpcStep) step).getNpcID()) continue;

				Shape hull = npc.getConvexHull();
				if (hull != null)
				{
					OverlayUtil.renderHoverableArea(
						graphics,
						hull,
						client.getMouseCanvasPosition(),
						config.itemOverlayColor(),
						config.itemOverlayColor(),
						config.itemOverlayColor()
					);

					if (config.itemOverlayTextMode() == HighlightOverlayTextSetting.NONE) continue;

					TextComponent textComponent = new TextComponent();
					textComponent.setColor(config.itemOverlayColor());
					textComponent.setText(step.getTooltip());
					Rectangle bounds = hull.getBounds();

					FontMetrics fontMetrics = graphics.getFontMetrics();
					int textWidth = fontMetrics.stringWidth(step.getTooltip());
					int textHeight = fontMetrics.getHeight();

					if (config.itemOverlayTextMode() == HighlightOverlayTextSetting.BELOW) {
						textComponent.setPosition(new Point(
							bounds.x + bounds.width / 2 - textWidth / 2,
							bounds.y + bounds.height + textHeight
						));
					} else {
						textComponent.setPosition(new Point(
							bounds.x + bounds.width / 2 - textWidth / 2,
							bounds.y - textHeight / 2
						));
					}

					textComponent.render(graphics);
				}
			}


        }

        return null;
    }
}

package com.toofifty.easyblastfurnace.utils;

import com.toofifty.easyblastfurnace.methods.Method;
import com.toofifty.easyblastfurnace.state.BlastFurnaceState;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.util.RSTimeUnit;

import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;

@Slf4j
public class FeesHelper {

    @Inject
    private BlastFurnaceState state;

    @Inject
    private Client client;

    @Inject
    private MethodHandler methodHandler;

    @Inject
    private ConfigManager configManager;

    private Instant staminaEndTime;

    private double lossRateMultiplier;


//	private boolean shouldCheckForemanFee() {
//		return client.getRealSkillLevel(Skill.SMITHING) < 60
//				&& (foremanTimer == null || Duration.between(Instant.now(), foremanTimer.getEndTime()).toSeconds() <= 30);
//	}
}

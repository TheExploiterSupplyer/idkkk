package com.example.sculkalertmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class SculkAlertMod implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {
        ModSounds.register();
    }

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                checkSculkNearby(client.player);
            }
        });
    }

    private void checkSculkNearby(PlayerEntity player) {
        BlockPos pos = player.getBlockPos();
        Box area = new Box(pos).expand(3);

        boolean foundSculk = false;

        for (int x = pos.getX() - 3; x <= pos.getX() + 3; x++) {
            for (int y = pos.getY() - 3; y <= pos.getY() + 3; y++) {
                for (int z = pos.getZ() - 3; z <= pos.getZ() + 3; z++) {
                    if (player.world.getBlockState(new BlockPos(x, y, z)).isOf(Blocks.SCULK)) {
                        foundSculk = true;
                        break;
                    }
                }
            }
        }

        if (foundSculk) {
            MinecraftClient.getInstance().player.playSound(ModSounds.SCULK_ALERT, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }
}

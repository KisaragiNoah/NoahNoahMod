package com.kisaraginoah.noahtorikese.item.record;

import com.kisaraginoah.noahtorikese.init.ModSounds;
import net.minecraft.world.item.Item;

public class AsuretikkuMusicDisc extends Item {
    public AsuretikkuMusicDisc() {
        super(new Properties().stacksTo(1).jukeboxPlayable(ModSounds.ASURETIKKU_KEY));
    }
}

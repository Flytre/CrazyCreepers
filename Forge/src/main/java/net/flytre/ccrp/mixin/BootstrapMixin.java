package net.flytre.ccrp.mixin;


import net.flytre.ccrp.Registry;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Bootstrap.class)
public class BootstrapMixin {

    @Unique
    private static boolean ranInit = false;


    @Inject(method = "initialize", at = @At("RETURN"))
    private static void flytre_lib$load(CallbackInfo ci) {
        if (!ranInit)
            Registry.init();
        ranInit = true;
    }

}

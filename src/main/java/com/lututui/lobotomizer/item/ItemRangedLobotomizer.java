package com.lututui.lobotomizer.item;

import java.util.List;

import com.lututui.lobotomizer.LobotomizerMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRangedLobotomizer extends Item {
    public ItemRangedLobotomizer() {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName(LobotomizerMod.MODID, "ranged_lobotomizer");
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        final ItemStack stack = playerIn.getHeldItem(handIn);

        if (playerIn.isSneaking()) {
            if (!worldIn.isRemote) {
                stack.setTagCompound(null);
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }

        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("target")) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if (!worldIn.isRemote) {
            final String targetEntity = stack.getTagCompound().getString("target");

            final List<EntityLiving> entitiesInRange = worldIn.getEntitiesWithinAABB(
                    EntityLiving.class,
                    playerIn.getEntityBoundingBox().grow(5)
            );

            LobotomizerMod.logger.info("Target entity: " + targetEntity);

            for (final EntityLiving e : entitiesInRange) {
                LobotomizerMod.logger.info("Entity in range: " + e.getClass().getName());
                if (e.isAIDisabled() || !e.getClass().getName().equals(targetEntity)) {
                    continue;
                }

                e.setNoAI(true);
                e.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100));
            }
        }

        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!(entity instanceof EntityLiving)) {
            return super.onLeftClickEntity(stack, player, entity);
        }

        if (!player.world.isRemote) {
            stack.setTagInfo("target", new NBTTagString(entity.getClass().getName()));

            LobotomizerMod.logger.info("Target set to " + entity.getClass().getName());
        }

        return true;
    }
}

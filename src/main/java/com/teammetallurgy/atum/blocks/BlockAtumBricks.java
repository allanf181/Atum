package com.teammetallurgy.atum.blocks;

import java.util.List;

import com.teammetallurgy.atum.Atum;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockAtumBricks extends Block {

    public BlockAtumBricks(String unlocalisedName) {
        super(Material.rock);
        this.setBlockName(unlocalisedName);
        this.setResistance(200000.0F);
        this.setBlockUnbreakable();
        this.setCreativeTab(Atum.creativeTab);
    }
    
    @Override
    public int damageDropped(int meta) {
        return meta;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(this,1,0));
        list.add(new ItemStack(this,1,1));
        list.add(new ItemStack(this,1,2));
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta != 2)
            return -1F;
        return 2.0F;
    }
    
    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta != 2)
            return 200000.0F;
        return 1.0F;
    }
}

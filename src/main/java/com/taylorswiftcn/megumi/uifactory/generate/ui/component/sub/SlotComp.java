package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "slot")
public class SlotComp extends BasicComponent {

    @ComponentField(define = "identifier")
    private String identifier;

    @ComponentField(define = "drawBackground")
    private Boolean drawBackground;

    private ItemStack item;

    public SlotComp(String id, String identifier) {
        this(id, identifier, new ItemStack(Material.AIR));
    }

    public SlotComp(String id, String identifier, ItemStack item) {
        super(id);
        this.identifier = identifier;
        this.item = item;
    }

    public BasicComponent setDrawBackground(Boolean drawBackground) {
        this.drawBackground = drawBackground;
        return this;
    }

    public static SlotComp getOriginSlot(Player player, int index) {
        if (0 > index || index > 45) return null;
        String identifier = String.format("container_%s", index);
        ItemStack item = player.getInventory().getItem(index);
        if (item == null || item.getItemMeta() == null) {
            return new SlotComp(identifier, identifier);
        }
        return new SlotComp(identifier, identifier, item);
    }

    public BasicComponent setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public BasicComponent setItem(ItemStack item) {
        this.item = item;
        return this;
    }
}

package xyz.damt.handlers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.util.CC;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VerificationHandler {

    private final Set<UUID> likedUsers;
    private final NameMC nameMC;

    private final Executor newThread = Executors.newFixedThreadPool(1);

    public VerificationHandler() {
        this.likedUsers = new HashSet<>();
        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
    }

    public void load() {
        this.nameMC.getConfig().getStringList("data.liked").forEach(s -> {
            likedUsers.add(UUID.fromString(s));
        });
    }

    public boolean isEmpty() {
        return this.likedUsers.isEmpty();
    }

    public int getSize() {
        return this.likedUsers.size();
    }

    public Set<UUID> getVerifiedUsers() {
        return this.likedUsers;
    }

    public void addUser(UUID uuid) {
        newThread.execute(() -> likedUsers.add(uuid));
    }

    public void removeUser(UUID uuid) {
        newThread.execute(() -> likedUsers.remove(uuid));
    }

    public boolean containsUser(UUID uuid) {
        return this.likedUsers.contains(uuid);
    }

    public void giveRewards(Player player) {
        List<String> places = new ArrayList<>();

        Objects.requireNonNull(nameMC.getConfig().getConfigurationSection("rewards")).getKeys(false).forEach(reward -> {
            if (!nameMC.getConfigHandler().getSettingsHandler().RANDOMIZE_REWARDS) {

                String path = "rewards." + reward + ".";
                if (nameMC.getConfig().getBoolean(path + "item.enabled")) {
                    String itemInfo = nameMC.getConfig().getString(path + "item.info");
                    String[] args = itemInfo.split(":");

                    ItemStack stack = new ItemStack(Material.valueOf(args[0]), Integer.parseInt(args[1]), (byte) Integer.parseInt(args[2]));
                    ItemMeta meta = stack.getItemMeta();

                    meta.setDisplayName(CC.translate(args[3]));
                    meta.setLore(CC.translate(nameMC.getConfig().getStringList(path + "item.lore")));
                    nameMC.getConfig().getStringList(path + "item.enchants").forEach(s -> {
                        String[] enchants = s.split(":");
                        Enchantment enchant = Enchantment.getByName(enchants[0]);
                        if (enchant == null) return;
                        stack.addUnsafeEnchantment(enchant, Integer.parseInt(args[1]));
                    });
                    stack.setItemMeta(meta);
                    player.getInventory().addItem(stack);
                }

                if (nameMC.getConfig().getBoolean(path + "command.enabled")) {
                    List<String> commands = nameMC.getConfig().getStringList(path + "command.commands");
                    commands.forEach(string -> {
                        nameMC.getServer().dispatchCommand(nameMC.getServer().getConsoleSender(), string.replace("{player}", player.getName()));
                    });
                }
                return;
            }

            places.add("rewards." + reward);

            if (!places.isEmpty()) {
                for (int i = 0; i < nameMC.getConfigHandler().getSettingsHandler().AMOUNT_OF_REWARDS; i++) {
                    int index = new Random().nextInt(places.size());

                    String item = places.get(index);
                    String path = item + ".";

                    if (nameMC.getConfig().getBoolean(path + "item.enabled")) {
                        String itemInfo = nameMC.getConfig().getString(path + "item.info");
                        String[] args = itemInfo.split(":");

                        ItemStack stack = new ItemStack(Material.valueOf(args[0]), Integer.parseInt(args[1]), (byte) Integer.parseInt(args[2]));
                        ItemMeta meta = stack.getItemMeta();

                        meta.setDisplayName(CC.translate(args[3]));
                        meta.setLore(CC.translate(nameMC.getConfig().getStringList(path + "item.lore")));
                        nameMC.getConfig().getStringList(path + "item.enchants").forEach(s -> {
                            String[] enchants = s.split(":");
                            Enchantment enchant = Enchantment.getByName(enchants[0]);
                            if (enchant == null) return;
                            stack.addUnsafeEnchantment(enchant, Integer.parseInt(args[1]));
                        });
                        stack.setItemMeta(meta);
                        player.getInventory().addItem(stack);
                    }

                    if (nameMC.getConfig().getBoolean(path + "command.enabled")) {
                        List<String> commands = nameMC.getConfig().getStringList(path + "command.commands");
                        commands.forEach(string -> {
                            nameMC.getServer().dispatchCommand(nameMC.getServer().getConsoleSender(), string.replace("{player}", player.getName()));
                        });
                    }
                }
            }
        });
    }

    public void removeAll() {
        newThread.execute(() -> {
            likedUsers.clear();
            nameMC.getConfig().set("data.liked", null);
        });
    }

    public void save() {
        newThread.execute(() -> {
            List<String> strings = new ArrayList<>();
            likedUsers.forEach(uuid -> strings.add(uuid.toString()));
            nameMC.getConfig().set("data.liked", strings);
            nameMC.saveConfig();
        });
    }

}

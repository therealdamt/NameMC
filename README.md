# NameMC

### Description
NameMC is a plugin that hooks up to NameMC to get if a user liked or not and gives out rewards. 

### Features
* Built-In API
* Fully configurable
* Flat file storage system
* Fully optimized [All registrations and savings are done async]

### API
NameMC hook contains a fully awesome API that legit lets you do whatever you want! Some features can be seen below:
* Custom Events
* Custom API
* API-Example at the end of the ReadME file.

Check out more of the API on the ``test`` package of the item! Or inside of the source code!

### Config
```yml
messages:
  no-permission: "&cYou do not have any permissions to execute this command!"
  invalid-value: "&cThat is an invalid value!"
  user:
    has-liked: "&aThank you for liking our NameMC page! Here is you're rewards!"
    already-liked: "&cWoops! Looks like you have already verified your like!"
    did-not-like: "&cWoopsie! Looks like you did not like our page! Please like at https://namemc.com/server/example.net"
  admin:
    wrong-usage:
      - "&6&lNameMC Verification Commands"
      - "&7- &6/namemc remove <user>"
      - "&7- &6/namemc status <user>"
      - "&7- &6/namemc add <user>"
      - "&7- &6/namemc removeall"
      - "&7- &6/namemc list"
    already-verified: "&cLooks like the user {user} is already verified!"
    is-not-verified: "&cLooks like the user {user} is not verified!"
    status-message: "&aLooks like the user {user} has {status}!"
    remove-message: "&cYou have removed the user {user} from the verification list!"
    add-message: "&aYou have added the user {user} to the verification list!"
    remove-all-message: "&cYou have removed all users that have verified their likes!"
    no-verifications: "&cThere are no verifications registered!"
    list-message: "&aThe users of which have verified their like are: {users}"

settings:
  server-ip: "example.net" #server ip | https://namemc.com/server/example.net #It would be "example.net"
  verify-command-permission: "verify.access"
  namemc-command-permission: "admin.verify.access"
  randomize-rewards: true
  amount-of-rewards: 2

rewards:
  reward1:
    item:
      enabled: true
      info: "DIRT:32:0:&cHi" #material:amount:data:name
      lore:
        - ""
      enchants:
        - ""
    command:
      enabled: true
      commands:
        - "give {player} dirt 1"

data:
  liked: {}
```

### API Example

```java
public class APIUsage implements Listener {

    private final NameMCAPI nameMCAPI = JavaPlugin.getPlugin(NameMC.class).getNameMCAPI();

    @EventHandler
    public void onPlayerVerifyEvent(PlayerVerifyEvent e) {
        Player player = e.getPlayer();

        if (!player.hasPermission("no.verify")) return;

        player.sendMessage(ChatColor.RED + "You may not verify yourself!");
        e.setCancelled(true);
    }

    @EventHandler
    public void onAdminVerifyEvent(AdminAddVerifyEvent e) {
        Player target = Bukkit.getPlayer(e.getTargetUUID());

        if (target == null) return;
        target.sendMessage("You have been verified!");
    }

    @EventHandler
    public void onAdminRemoveAllVerifyEvent(AdminRemoveAllVerifyEvent e) {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage("All verifications were removed!"));
    }

    @EventHandler
    public void onAdminRemoveVerifyEvent(AdminRemoveVerifyEvent e) {
        Player player = Bukkit.getPlayer(e.getTargetUUID());
        if (player == null) return;

        player.sendMessage("You're verification has been removed!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("Due to a special event we verify everyone of which joins right away!");
        if (!nameMCAPI.isVerified(e.getPlayer().getUniqueId()))
        nameMCAPI.addVerify(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Bukkit.getConsoleSender().sendMessage("The verification of the player " + e.getPlayer().getName() + " has been removed!");
        nameMCAPI.removeVerify(e.getPlayer().getUniqueId());
    }
}
```

***Credits***
This project was fully made by damt, you may **not** resell this product unless you changed something about it. You may **not** claim this proudct as your own.

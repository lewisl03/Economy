# MySQL Economy Plugin README

## Description

This is an Economy Plugin for Minecraft that utilizes MySQL for managing in-game currency and transactions.

## Commands

### /economy or /eco
- **Description:** Main economy command
- **Usage:** /economy
- **Aliases:**
  - eco

### /pay
- **Description:** Command used to pay someone
- **Usage:** /pay <amount> <player>

### /bal or /balance
- **Description:** Command used to check your balance
- **Usage:** /bal
- **Aliases:**
  - balance

### /withdraw
- **Description:** Command used to withdraw money from your balance
- **Usage:** /withdraw <amount>

### /baltop or /balancetop
- **Description:** Command used to see who has the highest balance
- **Usage:** /baltop
- **Aliases:**
  - balancetop

## MySQL Configuration

This plugin utilizes MySQL for storing and managing the economy data. Ensure you have a MySQL server set up with the required database and credentials.

1. Configure MySQL settings in the plugin's configuration file.

```yaml
mysql:
  host: <MySQL Host>
  port: <MySQL Port>
  database: <Database Name>
  username: <MySQL Username>
  password: <MySQL Password>
```

## How to Use

1. Set up the MySQL configuration in the plugin's configuration file.
2. Use the respective commands to manage your in-game currency and transactions.
3. View the highest balances using the `/baltop` command.
4. Transfer funds to other players using the `/pay` command.
5. Check your balance using the `/bal` or `/balance` command.

Feel free to contribute, report issues, or suggest improvements on our GitHub repository. Happy gaming!

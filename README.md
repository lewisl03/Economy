# Economy Plugin

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Overview

This is an Economy Plugin for Minecraft that utilizes MySQL for efficient management of in-game currency and transactions.

## Features

- Manages in-game economy using MySQL database
- Allows players to pay each other in-game currency
- Provides commands to check balance and withdraw money
- Displays the highest balance holders with `/baltop` command

## ToDo

- Tidy code
- customisable messages
- update api side

### Prerequisites

- Ensure the server is compatible with [Platform/Environment] (e.g., Spigot, Bukkit, etc.).

### Steps

1. Download the latest release from the [releases page](https://github.com/lewisl03/EconomyPlugin/releases).
2. Place the downloaded JAR file into the `plugins` directory of your server.
3. Start or restart your server to load the plugin.

## Usage

### Configuration

- Configure the MySQL settings in the plugin's `config.yml` file.

### Commands

- `/economy` or `/eco`: Main economy command.
- `/pay <amount> <player>`: Pay a specific amount to another player.
- `/bal` or `/balance`: Check your in-game currency balance.
- `/withdraw <amount>`: Withdraw money from your balance.
- `/baltop` or `/balancetop`: See the players with the highest balance.

### Permissions
- `economy.*`: Grants access to all plugin commands.
- `economy.default`: Grants access to all default commands.
- `economy.admin`: Grants access to all admin commands.

## Development

### Build from Source

To build the plugin from source, follow these steps:

1. Clone the repository: `git clone https://github.com/lewisl03/EconomyPlugin.git`.
2. Navigate to the project directory: `cd EconomyPlugin`.
3. Build the project using your build tool of choice (e.g., Maven, Gradle).

### Contributing

1. Fork the repository and create a new branch: `git checkout -b feature/new-feature`.
2. Make your changes and commit them: `git commit -m 'Add new feature'`.
3. Push to the branch: `git push origin feature/new-feature`.
4. Submit a pull request detailing your changes.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Mention any external libraries, tutorials, or references you used.

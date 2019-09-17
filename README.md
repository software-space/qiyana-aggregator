[![MIT Licensed](https://img.shields.io/badge/license-Apache2.0-green.svg)](https://github.com/fastboyz/Qiyana-Data-aggregator/blob/master/LICENSE)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fsoftware-space%2Fqiyana-aggregator.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fsoftware-space%2Fqiyana-aggregator?ref=badge_shield)
[![Build Status](https://dev.azure.com/softwarespace-hq/Qiyanna/_apis/build/status/software-space.qiyana-aggregator?branchName=master)](https://dev.azure.com/softwarespace-hq/Qiyanna/_build/latest?definitionId=1&branchName=master)


This Java project is meant to be a data aggregator for the riot API, The ultimate
goal is to offer Data aggregation and offer and API that will deliver it. 
Since the official Riot API does not have general stats, and the only other option
is the champions.gg API.

## Development environment  
### Needed:
 1. Docker. There is a docker compose file with the database setup, just run that in docker.
 2. in the pom file, there is a setting to change.
 3. You sill need to set these ENV variables:
    * QIYANNA_CONFIG_DATABASE_URL=jdbc:postgresql://localhost:54320/qiyanna
    * QIYANNA_CONFIG_DATABASE_USERNAME=qiyanna
    * QIYANNA_CONFIG_DATABASE_PASSWORD=qiyanna123
    * RIOT_API_KEY=YOUR API KEY
 4. Once you have the containers running, run the command `mvn clean install` in order to generated the table in the database and have the migrations be applied
 5. You are ready to run the app
 
 
 ## Disclaimer  
 Qyianna isn't endorsed by Riot Games and does not reflect the views or opinions of Riot Games or anyone officially
 involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered
 trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fsoftware-space%2Fqiyana-aggregator.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fsoftware-space%2Fqiyana-aggregator?ref=badge_large)

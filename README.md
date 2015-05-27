This game was inspired by Iyouboushi's mIRC BattleArena bot - https://github.com/Iyouboushi/mIRC-BattleArena

###Overview
Its more or less a dungeon crawling game, you fight monsters and procceeds to the lower floors.

###Combat System
The combat system is much more dynamic than the traditional turn-based game. Instead of having each side takes turn one after another, Mango RPG uses a continuous timeline. For example, in 2 seconds, one character can use 3 fast skills where areas another can use 1 slow skill.

###[Skill](https://github.com/MangoLion/EpicMangoRPG/wiki/Skill)
Skills are what the character use mainly in battle, even normal attacks are skills. A skill is composed of one or many steps, each step is independent and can be cancelled/interupted at anytime.

###[Event](https://github.com/MangoLion/EpicMangoRPG/wiki/Event)
While most skill's execution is instant, blade slicing through flesh, biting teeth, some skill does not show its effects at its execution, such as firing an arrow or throwing a grenade. When a character fired an arrow, he is free to do his own action while the arrow is still flying through the air, the arrow here would be an event.

###[Stats](https://github.com/MangoLion/EpicMangoRPG/wiki/Stats)
hp - your health  
mp - mana  
sp - stamina  
bal - balance determines some skills's damage, it also prevent your skills from being interrupted. When its below 10%, you have a chance of receiving the knockdown status when attacked  
str - increase damage of melee and bow attacks, also determines parry success chance  
int - increase damage of magic attacks  
dex - increase accuracy  
agi - decrease skills load/execution/cooldown time, decrease enemy accuracy when attacking you  
def/magicdef  - the damage you received is first subtracted by your defense  
prot - after defense is subtracted, its reduced by % of your protection, also reduces the chance of receiving a critical hit by prot*2 %.  
See the link for more.  



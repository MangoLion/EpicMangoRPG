# EpicMangoRPG
This game was inspired from Iyouboushi's mIRC BattleArena bot - https://github.com/Iyouboushi/mIRC-BattleArena

Overview:<br />
Its more or less a dungeon crawling game, you fight monsters and procceeds to the lower floors, with stronger ones. <br />
dl link: https://www.dropbox.com/s/diiqav7qyjzxwon/EpicMangoRPG.jar?dl=0

Combat system:<br />
The combat system is much more dynamic than the traditional turn-based game. Instead of having each side takes turn one after another, Mango RPG uses a continuous timeline. For example, in 4 seconds, one character can use 3 fast skills whereareas another can use 1 slow skill.

Skills:<br />
Skills are what the character use mainly in battle, even normal attacks are skills. A skill is composed of one or many steps, each step is independent and can be cancelled/interupted at anytime. For example, the skill sword combo composes of 3 slashes(steps), each slash deals more damage than the last, however, if the player is stunned or interupted while executing one of those 3 slashes, the skill is cancelled at the current slash(step). You may also cancel a skill mannually (not implemented). Each step has a damage modifier (%) of your base attack. Your base attack is calculated by your weapon's base damage and your str/int. For example, if a step deals 200% of your base damage, it means that step deals the same damage as 2 normal hits.

Skill interuption: when a character is attacked while its loading a skill, there is a chance that its skill will be cancelled, the higher the % of balance that character has, the lower the chances of interuption.

Skill parry/block/miss: most offensive skill has a chance of missing. When two melee skills are executed at the same time, there is a chance that these two skills will block or parry each other. Some skills cannot be blocked or parried, but most of the time dodged.

Events:<br />
While most skill's execution is instant, blade slicing through flesh, biting teeth, some skill does not show its effects at its execution, such as firing an arrow. When a character fired an arrow, he is free to do his own action while the arrow is still flying through the air, the arrow here would be an event. Some skills instead of dealing damage instantly, would create an event with a timer. An event is no different than a skill execution, it may be parried, dodged or blocked, only at its execution.<br />


Commmands:<br />
for using skills on targets:<br />
	Use skill [skillname] on [targetname]<br />
some skills can be executed for a desired time (Block and Wait): <br />
	Use skill [skillname] on [targetname] for [# of seconds]<br />
Using items:<br />
	Use [itemname]<br />
or:<br />
	Use [itemname] on [targetname]<br />

Stats:<br />
hp - your health<br />
mp - mana<br />
sp - stamina<br />
bal - balance determines some skills's damage, it also prevent your skills from being interupted. When its below 10%, you have a chance of receiving the knockdown status when attacked (not implemented)<br />
str - increase damage of melee attacks<br />
int -  increase damage of magic attacks<br />
dex - decrease the miss chance, increase the success chance of life skills such as scavenging<br />
agi - decrease skills load/execution/cooldown time, increase enemy's miss chance when attacking you<br />
def - the damage you received is first subtracted by your defense<br />
prot - after defense is subtracted, its reduced by % of your protection, prot also reduces the chance of receiving a critical hit.<br />

Tactics:<br />
If you think that the enemy's attack is not too dangerous, you can execute your attack and expect to receive the enemy's attack. If the incomming attack is dangerous and might kill you, the most effective way is to use parry, because it negates the enemy attack and also has a low cooldown which allows you to strike back. However, many skills cannot be parried (jump atk, body slam) then use block. Parry and block have the same weakness, that is their rather slow loading time (0.4 sec) which doesn't allow you to defend against many faster attacks. If all else fails, use dodge, while dodge can be used against most attacks (that are not AOE) and loads twice as fast, it has a much lower success rate than the other 2 defensive skills.
Over success rate: block - 100%, parry -80%, dodge -50%. Note that some skill has an even less succcess chance against parry or dodge.<br />

Weather and Terrain (not implemented)<br />
Temp - enhance or reduce some elemental skill damage, at extremities, it can cause bad status effects (heat stroke, freezing)<br />
Humid - enhance water skills, reduce fire skills<br />
Visibility - affects accuracy of attacks, especially ranged ones, will miss more often at low visibility<br />
Wind - affect accuracy of ranged attack, reduce burning status<br />
Rugged - how difficult a terrain is to navigate, affects many (idk what yet) factors<br />

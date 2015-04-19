# furry-dubstep
Make stupidly large emoticon mosaics on http://brawl.com

Once you've downloaded it, you'll want to open a terminal wherever you put the jar. Run the jar in some JRE like so:
```
java -jar emote.jar <path-to-image>
```
Where *path-to-image* is the absolute path to whatever image you want to convert. The program will print the emote "code" to the terminal, so you'll probably want to redirect the output into a file, like so:
```
java -jar emote.jar /home/phanta/lorem/ipsum.png > emotes.txt
```
The above snippet will turn the image ipsum.png into emotes and put the in emotes.txt, which will be created (or overwritten) wherever you ran the command from.

**WARNING: Try not to use the program with images too large, or it could cause a lot of lag for you when you try to paste the emote "code" into the forums. I suggest sticking to around 48x48 at maximum.**

Project name suggested by GitHub.

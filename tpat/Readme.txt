hi Georg,

exec:
./jdk1.8.0_152/bin/java -jar TPAT-1.2-jfx.jar

find:
myDocuments has the generated TPL files.

Note:
- Maybe you want to present already prepaired policies, and just show the usability (drag drop, what not...) and result,
 without having to build the entire policy from scratch.
- Undo should be working but not fully tested, so do not exagerate with it, better redo the step manually ;)
- simplest way to restore prepaired saves, is to simply git revert the files in folder 'saves'.

Best of the luck for the presentation,
Lukas
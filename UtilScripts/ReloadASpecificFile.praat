#----------Form to pass parameters to the script-------
form Param
sentence fullFileName "default"
sentence soundNumber "default"
sentence nameToGiveToTheFile "default"
endform
#-----------------------------------------------
#1 I reload the file
Read from file... 'fullFileName$'
#2 I gave it the name I need
temp$="Sound "+soundNumber$
writeInfoLine(temp$)
selectObject(temp$)
Rename... 'nameToGiveToTheFile$'

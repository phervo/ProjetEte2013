#----------Form to pass parameters to the script-------
form Param
sentence valueOfTheSoundToSave 0
sentence newNameOfTheSound "default"
endform
#-----------------------------------------------
#1st select the good sound
temp$="Sound "+valueOfTheSoundToSave$
writeInfoLine(temp$)
appendInfoLine(newNameOfTheSound$)
selectObject(temp$)
Save as WAV file... 'newNameOfTheSound$'
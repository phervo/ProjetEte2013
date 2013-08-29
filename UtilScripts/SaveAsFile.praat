#----------Form to pass parameters to the script-------
form Param
sentence valueOfTheSoundToSave 0
sentence newNameOfTheSound "default"
endform
#-----------------------------------------------
#1st select the good sound
temp$="Sound "+valueOfTheSoundToSave$
selectObject(temp$)
#2nd save it as wav
Save as WAV file... 'newNameOfTheSound$'
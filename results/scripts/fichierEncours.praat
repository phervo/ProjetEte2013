#-----------------------------------------------
# Project : Software synthesis using GA
# Hervo Pierre-Yves, automatic Script generated in java
#-----------------------------------------------
select Artword phon
# Supply lung energy
#-----------------------------------------------
Set target... 0.0    0.1 Lungs
Set target... 0.03     0.0 Lungs
Set target... 1.0     0.0 Lungs
#-----------------------------------------------
# Control glottis
#-----------------------------------------------
#Glottal closure
Set target... 0.0  0.5  Interarytenoid
Set target... 1.0  0.5 Interarytenoid
#
# Adduct vocal folds
Set target... 0.0   0.96 Cricothyroid
Set target... 1.0   0.29 Cricothyroid
# Close velopharyngeal port
#-----------------------------------------------
Set target... 0.0   0.68 LevatorPalatini
Set target... 1.0   0.92 LevatorPalatini
#-----------------------------------------------
#-----------------------------------------------
Set target... 0.0   0.75 Genioglossus
Set target... 1.0   0.55 Genioglossus
#
Set target... 0.0   0.82 Styloglossus
Set target... 1.0   0.33 Styloglossus
#
Set target... 0.0   0.55 Mylohyoid
Set target... 1.0   0.62 Mylohyoid
#
Set target... 0.0   0.15 OrbicularisOris
Set target... 1.0   0.85 OrbicularisOris
#-----------------------------------------------
# Shape mouth to open vowel
#-----------------------------------------------
# Lower the jaw
# -----------------------------------------
Set target... 0.0   -0.07 Masseter
Set target... 1.0   0.42 Masseter
# Pull tongue backwards
Set target... 0.0   0.01 Hyoglossus
Set target... 1.0   0.08 Hyoglossus
# Synthesise the sound
#-----------------------------------------------
select Artword phon
plus Speaker Robovox
To Sound... 22050 25   0 0 0    0 0 0    0 0 0
#-----------------------------------------------
#----------Part to rename the sound created-------
form Param
sentence value "default"
endform
Rename... 'value$'
#-----------------------------------------------
#-----------------------------------------------
# Automatic data extraction part
To Formant (burg)... 0 5 5500 0.025 50
numberOfFormant = Get number of formants... 1
writeInfoLine("number of formants :", numberOfFormant)
for intervalNumber from 1 to 3 
temp1 = Get value at time... intervalNumber 0.1 Hertz Linear
temp1$ = string$(temp1)
appendInfoLine("Valeur de temp1 ",temp1$)
temp2 = Get value at time... intervalNumber 0.25 Hertz Linear
temp2$ = string$(temp2)
appendInfoLine("Valeur de temp2 ",temp2)
temp3 = Get value at time... intervalNumber 0.5 Hertz Linear
temp3$ = string$(temp3)
appendInfoLine("Valeur de temp3 ",temp3)
temp4 = Get value at time... intervalNumber 0.9 Hertz Linear
temp4$ = string$(temp4)
appendInfoLine("Valeur de temp4 ",temp4)
#First verification : no --undefined-- values 
if temp1$ <> "--undefined--" and temp2$ <> "--undefined--" and temp3$ <> "--undefined--" and temp4$ <> "--undefined--"
#Second verification : value of mean in a good interval 
temp5 = Get mean... intervalNumber 0 0 Hertz
appendInfoLine("Valeur moyenne pour Formant",intervalNumber," :",temp5)
if intervalNumber = 1 and temp5 >= 230
appendInfoLine("On est ok interval 1")
varMeans[1]=temp5
elsif intervalNumber = 2 and temp5 >= 940
appendInfoLine("On est ok interval 2")
varMeans[2]=temp5
elsif intervalNumber = 3 and temp5 >= 2300
appendInfoLine("On est ok interval 3")
varMeans[3]=temp5
else
appendInfoLine("On est pas ok")
varMeans[1]=0
varMeans[2]=0
varMeans[3]=0
goto break
endif
else
appendInfoLine("undefined")
varMeans[1]=0
varMeans[2]=0
varMeans[3]=0
goto break
endif
endfor
label break
sendsocket localhost:2009 'varMeans[1]' 'varMeans[2]' 'varMeans[3]'

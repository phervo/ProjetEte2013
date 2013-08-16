#-----------------------------------------------
# Project : Software synthesis using GA
# Hervo Pierre-Yves, automatic Script generated in java
#-----------------------------------------------
#-----------------------------------------------
Create Speaker... Robovox Female 2
Create Artword... phon 1.0
#-----------------------------------------------
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
Set target... 0.0   0.58 Cricothyroid
Set target... 1.0   0.21 Cricothyroid
# Close velopharyngeal port
#-----------------------------------------------
Set target... 0.0   0.24 LevatorPalatini
Set target... 1.0   0.5 LevatorPalatini
#-----------------------------------------------
#-----------------------------------------------
Set target... 0.0   0.6 Genioglossus
Set target... 1.0   0.25 Genioglossus
#
Set target... 0.0   0.63 Styloglossus
Set target... 1.0   0.6 Styloglossus
#
Set target... 0.0   0.72 Mylohyoid
Set target... 1.0   0.98 Mylohyoid
#
Set target... 0.0   0.7 OrbicularisOris
Set target... 1.0   0.95 OrbicularisOris
#-----------------------------------------------
# Shape mouth to open vowel
#-----------------------------------------------
# Lower the jaw
# -----------------------------------------
Set target... 0.0   -0.5 Masseter
Set target... 1.0   -0.44 Masseter
# Pull tongue backwards
Set target... 0.0   0.14 Hyoglossus
Set target... 1.0   0.5 Hyoglossus
# Synthesise the sound
#-----------------------------------------------
select Artword phon
plus Speaker Robovox
To Sound... 22050 25   0 0 0    0 0 0    0 0 0
#-----------------------------------------------
#-----------------------------------------------
form Param
sentence value "default"
endform
Rename... 'value$'
#-----------------------------------------------
# Automatic data extraction part
To Formant (burg)... 0 5 5500 0.025 50
Speckle... 0 0 5500 30 yes
numberOfFormant = Get number of formants... 1
writeInfoLine("number of formants :", numberOfFormant)
for intervalNumber from 1 to numberOfFormant 
temp1 = Get value at time... intervalNumber 0.1 Hertz Linear
appendInfoLine("Valeur de temp1 ",temp1)
temp2 = Get value at time... intervalNumber 0.25 Hertz Linear
appendInfoLine("Valeur de temp2 ",temp2)
temp3 = Get value at time... intervalNumber 0.5 Hertz Linear
appendInfoLine("Valeur de temp3 ",temp3)
temp4 = Get value at time... intervalNumber 0.9 Hertz Linear
appendInfoLine("Valeur de temp4 ",temp4)
if temp1 <> "--undefined" 
different de undefined
else
undefined
endif
mymean = (temp1+temp2+temp3+temp4)/4 
appendInfoLine("Valeur moyenne du formant ",intervalNumber,":",mymean)
endfor

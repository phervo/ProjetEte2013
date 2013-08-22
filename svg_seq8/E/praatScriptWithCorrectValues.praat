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
Set target... 0.0   0.55 Cricothyroid
Set target... 1.0   0.55 Cricothyroid
# Close velopharyngeal port
#-----------------------------------------------
Set target... 0.0   0.97 LevatorPalatini
Set target... 1.0   0.97 LevatorPalatini
#-----------------------------------------------
#-----------------------------------------------
Set target... 0.0   0.88 Genioglossus
Set target... 1.0   0.88 Genioglossus
#
Set target... 0.0   0.07 Styloglossus
Set target... 1.0   0.07 Styloglossus
#
Set target... 0.0   0.11 Mylohyoid
Set target... 1.0   0.11 Mylohyoid
#
Set target... 0.0   0.05 OrbicularisOris
Set target... 1.0   0.05 OrbicularisOris
#-----------------------------------------------
# Shape mouth to open vowel
#-----------------------------------------------
# Lower the jaw
# -----------------------------------------
Set target... 0.0   0.47 Masseter
Set target... 1.0   0.47 Masseter
# Pull tongue backwards
Set target... 0.0   0.05 Hyoglossus
Set target... 1.0   0.05 Hyoglossus
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
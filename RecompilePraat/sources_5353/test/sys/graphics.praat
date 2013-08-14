# graphics.praat
# Paul Boersma, 5 January 2012

Erase all
Times
14
Select inner viewport... 0.1 0.4 0.1 8.9
Axes... 0 1 0 1
for i from 0 to 800
	grey = i/800
	Paint rectangle... 'grey' 0 1 i/801 (i+1)/801
endfor
Black
Draw inner box
Select inner viewport... 5.6 5.9 0.1 8.9
Axes... 0 1 18 -1
Line width... 1
Draw line... 0.3 -1 0.3 18
Line width... 2
Draw line... 0.5 -1 0.5 18
Line width... 3
Draw line... 0.7 -1 0.7 18
Line width... 2
Paint rectangle... 0.5 0 3 1 2
drawColour.y = 0
procedure drawColour colour$
	Paint rectangle... 'colour$' 0 1 .y .y+1
	'colour$'
	Draw line... 1.2 .y 1.1 .y+1
	Text... 1.2 left .y+0.5 half 'colour$'
	.y += 1
endproc
call drawColour Black
call drawColour White
call drawColour Red
call drawColour Green
call drawColour Blue
call drawColour Yellow
call drawColour Cyan
call drawColour Magenta
call drawColour Maroon
call drawColour Lime
call drawColour Navy
call drawColour Teal
call drawColour Purple
call drawColour Olive
call drawColour Pink
call drawColour Silver
call drawColour Grey
Black
Line width... 1
Select inner viewport... 0.6 1 0.1 1.9
Axes... 0 1 0 1
Draw line... 0 0 0 1
Text... 0 Left 1.0 Top top-aligned
Text... 0 Left 0.8 Half This text here is
Text... 0 Left 0.6 Half left-aligned text
Text... 0 Left 0.4 Half \ae\:fnd\0v \dh\ics \swz w\ef\l~.
Text... 0 Left 0.2 Half many----hyphens
Text... 0 Left 0.0 Bottom bottom-aligned
Text... 0 Left -0.2 Bottom Kate\r<ina
Text... 0 Left -0.4 Bottom Kateřina
Text... 0 Left -0.6 Bottom 한국
Select inner viewport... 2.8 3.2 0.1 1.9
Dotted line
Draw line... 0.5 0 0.5 1
Solid line
Text... 0.5 Centre 1.0 Top top-aligned
Text... 0.5 Centre 0.8 Half This text here is
Text... 0.5 Centre 0.6 Half centred text
Text... 0.5 Centre 0.4 Half \aeːnd\0v \dh\ics \swz w\ef\l~.
Text... 0.5 Centre 0.2 Half many----hyphens
Text... 0.5 Centre 0.0 Bottom bottom-aligned
Select inner viewport... 5 5.4 0.1 1.9
Draw line... 1 0 1 1
Text... 1 Right 1.0 Top top-aligned
Text... 1 Right 0.8 Half This text here is
Text... 1 Right 0.6 Half right-aligned text
Text... 1 Right 0.4 Half \aeːnd\0v \dh\ics \swz w\ef\l~.
Text... 1 Right 0.2 Half many----hyphens
Text... 1 Right 0.0 Bottom bottom-aligned
Text... 1 Right -0.2 Bottom arkadaş
Text... 1 Right -0.4 Bottom حبيب
Text... 1 Right -0.6 Bottom 日本
Select inner viewport... 2.5 3.5 2.6 3.6
for i to 10
	Draw circle (mm)... 0.5 0.5 i
endfor
Blue
for i to 36
	.x = cos (i * pi / 18)
	.y = sin (i * pi / 18)
	Draw arrow... 0.5+0.3*.x 0.5+0.3*.y 0.5+0.7*.x 0.5+0.7*.y
endfor
for i from 10 to 20
	Draw ellipse... 0.5-i/10 0.5+i/10 -0.3 1.3
endfor
Select inner viewport... 0 6 4 5
for i to 10
	Paint circle (mm)... green i/12 0.7 i
	Paint ellipse... pink (11.9-i)/12 (11.1-i)/12 0.3-i/30 0.3+i/30
endfor
Select outer viewport... 0 6 5 5.5
Text bottom... yes 𝄐𝄑
Select inner viewport... 0 6 5 6
Purple
Draw rounded rectangle... 0.2 0.4 0.3 0.7 3
Paint rounded rectangle... purple 0.6 0.8 0.3 0.7 3
Select inner viewport... 0 6 6 7
call drawLines 1 0.1
call drawLines 2 0.3
call drawLines 3 0.5
call drawLines 5 0.7
procedure drawLines .width .x
	Line width... .width
	Dashed line
	Draw line... .x 0.65 .x+0.1 0.65
	Dotted line
	Draw line... .x 0.55 .x+0.1 0.55
	Dashed-dotted line
	Draw line... .x 0.45 .x+0.1 0.45
	Solid line
	Draw line... .x 0.35 .x+0.1 0.35
endproc
Line width... 1
Select inner viewport... 0.7 2.7 7 9
Create simple Matrix... hundred 2 100 randomUniform(0,100)
To Polygon
Salesperson... 1000
Draw closed... 0 0 0 0
Create simple Matrix... circle 2 100000
... if row=1 then cos(2*pi*col/100000) else sin(2*pi*col/100000) fi
To Polygon
Draw... 0 0 0 0
Black
Create simple Matrix... checker 10 10 x*y
Select inner viewport... 3.3 5.3 7 9
Paint cells... 0 0 0 0 0 0
Insert picture from file... /Users/pboersma/Praats/test/sys/paul.jpg 10 12 10 12
Select outer viewport... 0 6.5 0 9
;exit
Save as praat picture file... kanweg.prapic
Erase all
Read from praat picture file... kanweg.prapic
deleteFile ("kanweg.prapic")

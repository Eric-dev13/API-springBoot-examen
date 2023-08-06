--
-- Déchargement des données de la table user
--
INSERT INTO user (id, avatar, created_at, email, firstname, is_verified, lastname, password, pseudo, role, updated_at) VALUES
(1, NULL, '2023-07-23 11:16:10.472996', 'lanzae32@gmail.com', NULL, b'0', NULL, '$2a$10$adXxyDL/K/4Lf2sx1syJqOKxWy0E714q9Ifna16sI/kbGyOd29y82', 'admin', 'ADMIN', NULL),
(2, NULL, '2023-07-23 11:31:53.420574', 'usergmail.com', NULL, b'0', NULL, '$2a$10$RUvHLtjyVyU5Lj49T5vbVO9/S0Duz0MZPgDcjZGdMWFd8/RuMzfL6', 'user', 'USER', NULL);
-- COMMIT;

--
-- Déchargement des données de la table edibility
--
INSERT INTO edibility (id, name, path) VALUES 
(1, 'Mortel', 'mortel.png'),
(2, 'Toxique', 'toxique.png'),
(3, 'A rejeter', 'rejeter.png'),
(4, 'Comestible médiocre', 'mediocre.png'),
(5, 'Bon comestible', 'bon.png'),
(6, 'Excellent comestible', 'excellent.png');

--
-- Déchargement des données de la table lamellatype
--
INSERT INTO lamella_type (id, name, path, created_at) VALUES
(1, 'Adnées', 'adnees.png', '2021-12-04 11:30:15'),
(2, 'Décurrentes', 'decurrentes.png', '2021-12-04 11:30:15'),
(3, 'Echancrées', 'echancrees.png', '2021-12-04 11:30:15'),
(4, 'Emarginées', 'emarginees.png', '2021-12-04 11:30:15'),
(5, 'Libres', 'libres.png', '2021-12-04 11:30:15'),
(6, 'Sécédentes', 'secedentes.png', '2021-12-04 11:30:15'),
(7, 'Sinuées', 'sinuees.png', '2021-12-04 11:30:15'),
(8, 'Subdécurrentes', 'subdecurrentes.png', '2021-12-04 11:30:15');

--
-- Déchargement des données de la table mushroom
--
INSERT INTO mushroom (id, lamellatype_id, edibility_id, created_at, updated_at, visibility, commonname, latinname, flesh, hat, lamella, foot, habitat, comment, slug) VALUES
(1, NULL, 6, '2021-11-04 00:00:00', '2021-12-07 05:30:25', 1, 'Cèpe de Bordeaux', 'BOLETUS EDULIS', 'blanche.', 'de 10 à 20 cm, hémisphérique puis convexe, charnu, de couleur allant du beige au marron à brun noisette ou brun ochracé clair, parfois plus clair selon exposition, à marge épaisse, souvent excédente et généralement soulignée d\'un fin liseré blanchâtre.', 'tubes fins et serrés prolongés de pores d\'abord de couleur blanche à blanchâtre puis jaune, devenant ensuite verdâtre sur la fin.', 'robuste et ferme, renflé à ventru ou allongé en massue vers la base, de couleur fauve strié d\'un fin réseau blanc en saillie, devenant blanc vers la base.', NULL, 'excellent comestible.', 'cepe-de-bordeaux'),
(2, NULL, 6, '2021-11-04 00:00:00', '2021-11-26 11:32:34', 1, 'Cèpe bronzé', 'BOLETUS AEREUS', 'saveur et odeur douces.', 'de 10 à 20 cm hémisphérique puis convexe, charnu, à surface plus ou moins bosselée et cuticule veloutée devenant mate, à marge lisse et régulière devenant très faiblement sinueuse, de couleur brun foncé à reflets bronzés, brun ochracés ou chamois, parfois plus clair selon l\'exposition.', 'tubes fins de couleur blanchâtre devenant crème puis jaune à jaune verdâtre en vieillissant', '6 à 10 cm, très trapu, robuste, renflé à la base, obèse même, de couleur chamois à roux, strié d\'un fin réseau d\'abord blanc puis brun ; en bordure de sentier, lorsqu\'il est bien exposé, il est parfois très court , le camouflant ainsi davantage dans la végétation ...', 'thermophile, il pousse de la fin de l\'été au début de l\'hiver, généralement dans les chênaies aérées.', 'La chair du bolet bronzé est blanche et ferme lorsqu\'il est frais et les spores sont de couleur brun olivâtre. Rarement isolé, il est le plus souvent entouré de plusieurs individus de la même espèce. Ses couleurs peuvent être claires ou sombres et il est assez fréquent de voir des individus à la cuticule aux couleurs zonées. Il s\'agit d\'un des plus fins champignons des bois qui dégage son arôme avec intensité à la cuisson.', 'cepe-bronze'),
(3, NULL, 2, '2021-11-04 00:00:00', NULL, 1, 'Amanite tue mouches', 'AMANITA MUSCARIA', 'blanche.', 'après avoir émergé du sol, le chapeau est couvert de nombreuses verrues blanches en forme de pyramides.', 'libres, sont blanches, de même que l\'empreinte de spore.', 'est blanc, il mesure 5 à 20 centimètres de haut pour 1 à 2 centimètres de diamètre, et a la texture fibreuse et légèrement friable typique de la plupart des grands champignons. À sa base, la volve (ou bulbe) porte des résidus du voile universel sous la forme d\'un ou deux anneaux concentriques.', 'Amanita muscaria est un champignon cosmopolite, qui croît dans les forêts de conifères et de feuillus de toutes les régions tempérées et boréales de l\'hémisphère Nord.', 'toxique, hallucinogène.', 'amanite-tue-mouche'),
(4, NULL, 6, '2021-11-04 00:00:00', '2021-11-23 20:23:57', 1, 'Amanite des césars', 'AMANITA CAESAREA', 'ferme, blanche, jaune sous la cuticule. La marge est nettement striée, jaune d\'or.', 'de 8 à 15-20 cm, est ovoïde jeune puis hémisphérique et enfin convexe. La cuticule est rouge orangé vif, luisante, assez souvent couvert de grands lambeaux de la volve, blancs.', 'serrées, jaune d\'or clair.', 'de 8 à 15 cm est robuste, droit ou légèrement courbé, de la couleur des lames. La base du stipe est ovoïde vers le sol. La volve est épaisse, blanc grisâtre, s\'écartant du stipe et ample.Son anneau membraneux, concolore au pied, placé haut et pendant. L\'odeur est agréable, sa saveur douce de noix. La sporée est blanche.', NULL, 'sa réputation d\'excellent comestible, sa beauté et sa relative rareté en font un champignon recherché.', 'amanite-des-cesars'),
(5, NULL, 3, '2021-11-04 00:00:00', '2021-11-23 20:24:34', 1, 'Bolet enduit', 'SUILLUS COLLINITUS', 'de blanchâtre à jaune pâle, plus soutenu dans le pied, non grisonnante ni bleuissante, ferme jeune vite amollie, goût douceâtre, légèrement acidulée, odeur agréable, épicée', 'de 4 à 10/15 cm., globuleux à hémisphérique puis convexe à plan-convexe ;\r\ncuticule visqueuse, collante, brillante, facile à séparer, beige jaunâtre à orangé jaunâtre, orangé ocre ou encore brun ;\r\nmarge concolore, incurvée, un peu excédante .', 'adnés à décurrents, séparables, jaune pâle puis se salissant dans l\'âge - pores fins, réguliers, anguleux, jaune crème pâle puis jaunâtres à jaune vif, exsudant des gouttes laiteuses chez les jeunes spécimens.', '4-10 cm de hauteur, 1 à 2 cm de diam., élancé, égal ou partie inférieure renflée et parfois coudée, base atténuée, plein et mou, blanchâtre à jaunâtre, brunâtre en partie inférieure, ponctué de granulations blanchâtres laiteuses puis jaunissantes et brunissantes.', 'sous les pins et plus particulièrement pins d’Alep et les milieux herbeux. C\'est une espèce très commune que l\'on rencontre du mois de juillet au mois d\'octobre, toujours sous les pins ou peu éloigné d\'eux, mais toujours en étroite relation avec les racines (mycorhizes), et généralement en terrain calcaire.', 'considéré comme comestible. il reste cependant laxatif.', 'bolet-enduit'),
(6, NULL, 5, '2021-11-04 00:00:00', NULL, 1, 'Bolet jaune', 'SUILLUS LUTEUS', 'épaisse, tendre, aqueuse, cotonneuse dans l\'âge, blanchâtre, jaune vers les tubes, immuable à la coupe, bonne odeur fruitée, saveur légèrement acidulée', 'de 5 à 12 cm, hémisphérique puis convexe, à marge lisse, de couleur brune à brun ochracé, parfois nettement plus clair et jusqu\'à jaune, à l\'aspect brillant et visqueux.', 'relativement longs, adnés à légèrement décurrents, séparables, jaunes ;\r\npores très petits ronds puis anguleux dans l\'âge, concolores aux tubes puis brun-olivâtre, immuables à la pression.', '4-10 x 1,5-3 cm, plein, ferme, cylindrique, court et massif, blanchâtre à jaunâtre, plutôt granuleux vers le sommet .\r\nAnneau membraneux, ample, blanchâtre teinté de violet', 'Sous les pins', 'bon comestible à l\'état juvénile.\r\nIl porte un anneau membraneux formé d\'un voile tendu entre le haut du pied et la marge du chapeau', 'bolet-jaune'),
(7, NULL, 4, '2021-11-04 00:00:00', NULL, 1, 'Bolet moucheté', 'SUILLUS VARIEGATUS', 'blanc, Crème, Jaune.', 'brun, Crème, Gris, Jaune. Entièrement feutré mécheux ou moucheté.', 'brun, Jaune, Vert.', 'brun, Crème, Gris, Jaune.', 'Sous les pins sur sol calcaire', 'comestible.', 'bolet-mouchete'),
(8, NULL, 6, '2021-11-04 00:00:00', NULL, 1, 'Lactaire sanguin', 'LACTARIUS SANGUIFLUUS', 'blanchatre a la coupe puis sécretion d\'un latex rouge vineux', 'blanc cassé à orangé,verdissant.', 'rosé à reflets vineux, tachées de vert et de rouge vineux dans les blessures.', 'rosé vineux pâle,blanchâtre au sommet,vite creux.', 'sous les pins maritimes, d’Alep..., en mélange avec chênes, arbousiers, bruyères,romarin.', 'excellent comestible.', 'lactaire_sanguin'),
(9, NULL, 5, '2021-11-04 00:00:00', NULL, 1, 'Lactaire délicieux', 'LACTARIUS DELICIOSUS', 'blanchâtre se colorant d\'un lait couleur carotte.', 'convexe puis creusé, orangé,marqué de petite tache concentrique,tache blanche, verdissant dans les blessures.', 'orangé se tachant orange vif puis verdissant aux endroits blessés.', 'blanchâtre à orangé pâle, creux.', 'en automne sous pins maritimes,sylvestre, en forêts mixtes pins, chênes, bruyère.', 'Bon comestible', 'lactaire-delicieux'),
(10, NULL, 4, '2021-11-04 00:00:00', NULL, 1, 'Bolet joli', 'LECCINUM LEPIDUM', NULL, NULL, NULL, NULL, NULL, NULL, 'bolet-joli'),
(11, NULL, 3, '2021-11-04 00:00:00', NULL, 1, 'Lactaire des lieux sauvages', 'LACTARIUS TESQUORUM', 'latex blanc saveur âcre.', 'Crème, Jaune, Saumon', 'Crème, Jaune, Saumon.', 'Blanc, Crème, Saumon.', 'sous les pins.', 'Sans intérêt.', 'lactaire-des-lieux-sauvages'),
(12, NULL, 6, '2021-11-04 00:00:00', NULL, 1, 'Russule verdoyante', 'RUSSULA VIRESCENS', 'blanche avec une odeur fongique faible et une saveur de noisette', 'chapeau vert allant du vert pâle au vert-bleu-gris mesurant de 5 à 10 cm, souvent craquelé et parsemé de petites plaques lui donnant un aspect de moisi.', 'blanc-crème, assez serrées.', 'est blanc, cassant (comme chez toutes les russules) et mesure 1,5 à 2 cm de diamètre pour 4 à 8 cm de hauteur.', 'Sous feuillus(chêne, hêtre, châtaignier),sous conifères.', 'excellent comestible, sans doute la meilleure des russules.', 'russule-verdoyante'),
(13, NULL, 5, '2021-11-04 00:00:00', '2021-11-23 20:33:59', 1, 'Lepiote élevée', 'MACROLEPIOTA PROCERA', 'est blanche, molle, avec une odeur et une saveur fruitées agréables.', 'ovoïde puis convexe et enfin largement étalé en parasol, de 10 à 30 cm de diamètre, surmonté d\'un mamelon, avec une cuticule sèche, gris-roux, couvert d\'écailles brunes, plus denses vers le centre.', 'blanches, serrées, molles', 'haut de 15 à 40 cm, est élancé, creux, bulbeux à la base, brun tigré et de plus en plus écaillé en allant vers la base. Il est pourvu d\'un anneau double, blanchâtre et coulissant. Le pied est fibreux, ce qui le rend immangeable.', 'sur sol à tendance acide de type schiteux. sous-bois dégagés ou les clairières.', 'Comestible recherché.', 'lepiote-eleve'),
(14, NULL, 6, '2021-11-04 00:00:00', NULL, 1, 'Cèpe d\'été', 'BOLETUS AESTIVALIS', 'blanche, épaisse, ferme.', 'jusqu’à 25 cm, hémisphérique puis convexe, sec, velouté, brun noisette.', 'blancs puis jaunâtres ou ocre.', 'de 5 à 15 cm, ventru, allongé, réseau saillant, brun plus pâle que le chapeau.', 'bois de feuillus, lisières, souvent sous hêtres & chênes, clairières.', 'de Mai à Septembre\r\nLe cèpes d’été est un des meilleurs cèpes, incontestablement ! C’est le plus précoce, mais on le trouve rarement après le mois de septembre.', 'cepe-d-ete'),
(15, NULL, 5, '2021-11-04 00:00:00', '2021-11-23 20:36:01', 1, 'Chanterelle comestible', 'CANTHARELLUS CIBARIUS', 'compacte, blanc-crème, légèrement fibreuse dans le pied, jamais attaquée par les larves', 'de 2 à 12 cm, convexe puis centre légèrement déprimé avec l\'âge, d\'une belle couleur jaune d\'œuf .\r\nmarge d\'abord enroulée, puis sinueuse, lobée et fragile .\r\ncuticule séparable, sèche, unie.', 'assez espacés, parfois ressemblant à de grossières lamelles, fourchus, largement décurrents sur le stipe', 'de 4 à 7 cm et ~1,5 de diam, charnu, plein, inégal et s\'amincissant vers la base, sec, concolore au chapeau', 'Les girolles se ramassent de juin à novembre, selon les conditions météorologiques, dans les bois sous les feuillus ou les conifères, et poussent en groupes, c\'est à dire qu\'il est bien rare de ne trouver qu\'une chanterelle : elles sont souvent nombreuses sur une petite zone, dès lors que le sol est chaud et humide. Les régions de prédilection des girolles sont la Sologne, le sud-ouest, le massif central, les Vosges.', 'Sa croissance est assez lente : 1 à 2 semaines pour arriver à maturité, mais les girolles ont une longévité pouvant atteindre 1 mois. \r\nConfusion possible avec le clitocybe de l\'olivier (Omphalotus olearius). Toutefois, les deux sont quand même très différents : le clitocybe de l\'olivier est souvent beaucoup plus gros, il possède de vraies lames sous le chapeau, un pied strié et il pousse essentiellement sur des vieilles souches de bois, en touffes.', 'chanterelle-comestible'),
(16, NULL, 5, '2021-11-04 00:00:00', NULL, 1, 'Chanterelle jaunissante', 'CANTHARELLUS LUTESCENS', 'fibreuse. odeur fruitée saveur douce.', 'de 2 à 5 cm jaune brun ; Marge ondulée.', 'veiné, jaune pâle, sporée crème.', 'arrondi à aplati, creux, jaune, creux, jaune orangé.', 'en colonie sous feuillus, conifère souvent sous bruyère. Sur sol a tendance acide de type schisteux dans le sud de la France.', 'bon comestible.', 'chanterelle-jaunissante'),
(17, NULL, 4, '2021-11-04 00:00:00', '2021-11-23 20:37:29', 1, 'Clavaire jaune', 'RAMARIA FLAVA', 'blanchâtre. Odeur légère, agréable.', 'non différencié, forme coralloïde, Haut. 5 à 12 cm, Diam. 6 à 15 cm.', 'lisse.', 'tronc épais.', 'sous les chênes.', 'comestible médiocre.', 'clavaire-jaune'),
(18, NULL, 5, '2021-11-04 00:00:00', '2021-12-09 22:39:29', 1, 'Coprin chevelu', 'COPRINUS COMATUS', NULL, 'de 5 à 12 cm de hauteur, est blanc, cylindrique et couvert de peluches blanchâtres. Son sommet est plus foncé et nu. En vieillissant, il s\'évase pour former des cloches ; le bord noircit et exsude des gouttelettes noires. Au-dessous, les lames d\'abord blanches noircissent.', 'blanches virent rapidement au rose puis au noir.  L\'anneau est léger, mobile, et ne présente pas de volve.', 'long (jusqu\'à plus de 20 cm) est creux et cylindrique, un peu renflé en bulbe, blanc, avec un petit anneau en forme de bague qui finit par se détacher.', 'Le coprin chevelu apparaît dans la pelouse, dans les prés, au bord de chemins et des routes. Il apprécie les sols riches en matière organique, notamment ceux qui ont bénéficié d\'apports de fumier. Il décompose et recycle cette matière organique dans le sol. Ce champignon est un excellent indicateur d\'une terre riche en matière organique et en azote et donc idéal pour des graminées à gazon. Il pousse dans toutes les régions.', 'considéré comme un excellent comestible, à condition toutefois de le consommer jeune, c\'est-à-dire entièrement blanc à la coupe, s\'il y a du noir il est à rejeter.', 'coprin-chevelu'),
(19, NULL, 2, '2021-11-04 00:00:00', NULL, 1, 'Pezize étoilée', 'SARCOSPHAERA CORONARIA', 'blanchâtre à violacée, cassante. Odeur et saveurs faible.', NULL, 'en forme de pomme de terre, blanchâtre à l\'état jeune puis s\'ouvrant en étoile. Face intérieur( hyménium) blanchâtre puis vite violet et enfin brun violet. face exterieur blanchetachée de jaune.', NULL, 'Sous conifères, chênes sur sol calcaire.', 'toxique(mortel cru).', 'pezize-etoile'),
(20, NULL, 6, '2021-11-04 00:00:00', '2021-12-09 22:39:28', 1, 'Hydne commun', 'Hydnum repandum', 'épaisse, ferme, cassante, blanche fonçant légèrement au contact de l\'air, très légère odeur agréable, saveur parfois légèrement amère.', 'de 4 à 15 cm, massif, de forme très variable, irrégulièrement bosselé, souvent soudé les uns aux autres, convexe puis étalé, déprimé ;\r\ncuticule adnée, sèche, mate, de crème à chamois, non zonée et non écailleuse ;\r\nmarge épaisse, largement enroulée au début puis s\'étalant et devenant lobée et sinueuse.', 'serrés, décurrents, longs de 3-5 mm, fragiles, blancs puis crèmes et brunissants.', 'épais, charnu, trapu, plein, de 3 à 8 x 1 à 4 cm, difforme, excentré rarement central, s\'épaississant à la base, blanchâtre puis roussissant par endroits.', NULL, 'c\'est un excellent comestible, mais non de premier choix, sa chair présentant souvent l\'inconvénient d\'une légère amertume.', 'hydne-commun'),
(21, NULL, 5, '2021-11-04 00:00:00', NULL, 1, 'Chanterelle violette', 'Gomphus clavatus', 'blanche et ferme.', '5 à 10 cm de diamètre, un peu déprimé au sommet; marge sinueuse, devenant lobée; lilas, puis brun-jaune.', 'sinueux, fourchus et boursoufflés, violacés, puis brun-ocre.', 'court, souvent latéral, plus mince en bas, violet, devenant plus terne avec l\'âge.', 'conifères ( pins, sapins ) surtout en montagne.Souvent en touffes.Fin de l\' été et automne.Peu courant à assez courant selon les régions.', 'bon comestible jeune, légèrement amer par la suite.Ce champignon est apprécié pour sa chair plus tendre que la girolle mais il est aussi moins parfumé que cette dernière.', 'chanterelle-violette'),
(22, NULL, 3, '2021-11-04 00:00:00', '2021-12-09 22:39:28', 1, 'Géastre sessile', 'Geastrum fimbriatum', 'molle blanche à grise.', 'Sphérique de 2 à 4 cm de diamètre avant ouverture.\r\nIl éclate en 6 à 10 segments ocre avec une boule au centre allant de l\' ocre au gris parfois brun surmontée d\' une pointe plus sombre.', NULL, NULL, NULL, 'non comestible', 'geastre-sessile'),
(24, NULL, 5, '2021-11-04 00:00:00', '2021-11-23 20:41:55', 1, 'Clitocybe anisé', 'clitocybe odora', 'blanc sale à bleutée, élastique, peu épaisse sauf au disque, odeur forte d\'anis, saveur douce fortement anisée', '6 (10) cm de diam, convexe puis plat, légèrement omboné-déprimé ;\r\nrevêtement vert finement fibrilleux, vert, bleu-vert surtout au disque, pâlissant et décoloré dans l\'âge, sec, non visqueux ;\r\nmarge', 'adnées, très légèrement décurrentes, blanchâtres à concolores au chapeau mais plus pâles, inégales, moyennement serrées, parfois fourchues, veinées sur la face et interveinées dans le fond, surtout sur vieux sujets', '8 x 1,5 cm, cylindrique, blanchâtre plus ou moins bleuté verdâtre, pruineux, floconneux-fibrilleux, base velue légèrement épaissie.', NULL, 'bon comestible, mais très (trop) parfumé, à utiliser de préférence en condiment', 'clitocybe-anise'),
(25, NULL, 6, '2021-11-04 00:00:00', '2021-11-23 20:42:35', 1, 'Morille conique', 'Morchella elata', 'Crème, peu épaisse', '6 à 12 cm, conique, brun à brun-noir, côtes longitudinales rectilignes.', 'alvéoles irrégulières', 'jusqu’à 12 cm, trapu, crème, creux, granuleux', NULL, NULL, 'morille-conique'),
(26, NULL, NULL, '2021-11-04 00:00:00', '2022-03-03 17:05:49', 1, 'Amadouvier', 'Fomes fomentarius', 'La chair est subéreuse, très difficile à couper, de couleur fauve à brun foncé. La croûte est épaisse, très coriace, gris noirâtre et brillante à la coupe. Les exemplaires frais dégagent une odeur de pomme.', 'Le chapeau, de 10 à 40, voire 50 cm de large, et de 10 à 20 cm d\'épaisseur, en sabot de cheval, gris pâle, blanchissant, avec une marge crème à brun noisette est en réalité une concrétion de multiples chapeaux empilés les uns sous les autres. Ils sont ainsi gravés de sillons concentriques délimitant des bourrelets de taille croissante en allant vers la base, indiquant chaque nouvelle fructification.', 'Les tubes sont longs, bruns et totalement stratifiés car, ici encore, à chaque poussée de ce champignon pérennant , une nouvelle couche de tubes recouvre la précédente. Les pores sont gris clair à noisette, petits, arrondis, environ 0,2 mm. La sporée est jaunâtre.', 'il n\'y a pas de pied, le champignon pousse en console.', 'C\'est un redoutable parasite de plusieurs feuillus : hêtre, platane, bouleau, peuplier, chêne ou marronnier, entre autres. Il se fixe sur des arbres faibles ou blessés, produit une pourriture blanche et finit en quelques années par tuer son hôte.', 'l\'amadouvier n\'est pas comestible. Il était utilisé comme substance inflammable dès la Préhistoire.', 'amadouvier'),
(27, NULL, 3, '2021-11-04 00:00:00', NULL, 1, 'Rhizopogon rose', 'Rhizopogon', NULL, 'en forme de pomme de terre, blanchâtre à jaune.', 'gléba spongieux,jaunâtre puis olive foncé à maturation. Sporée olivâtre.', NULL, 'à demi enterrré sous les pins en automne.', 'A rejeter. Il existe des nombreuse espèces de rhizopogon, très difficile à différencier les unes des autres.', 'rhizopogon-rose'),
(28, NULL, 4, '2021-11-04 00:00:00', '2021-11-23 20:44:06', 1, 'Vesse-de-loup perlée', 'Lycoperdon perlatum', 'blanc bistre.', 'de 3 à 5 cm pour 4 à 9 cm de hauteur, en forme de globe, d\'ampoule globuleuse ou de poire inversée, à mamelon obtus plus ou moins marqué, blanc à blanc crème brunissant, couverte de petites aspérités coniques dont une partie disparaît en vieillissant.', 'néant', 'non marqué, dans le prolongement du carpophore, également recouvert des mêmes aspérités que celui-ci', 'principalement dans les bois de conifères, en lisières, talus et chemins les bordant, parfois aussi dans les herbus contigus moins fréquemment sous les feuillus', 'considérées comme comestibles quand elles sont jeunes et que la gleba est homogène et blanche.', 'vesse-de-loup-perlee'),
(38, NULL, NULL, '2022-04-27 04:44:28', NULL, 1, 'Test Non commun', 'Nom latin', 'Chair', 'Chapeau', 'Lamelle', 'Pied', 'Habitat', 'commentaires', 'test-slug');

--
-- Déchargement des données de la table localname
--
INSERT INTO localname (id, mushroom_id, created_at, name) VALUES
(2, 9, '2021-12-09 22:54:42', 'Safrané'),
(3, 8, '2021-12-09 22:55:52', 'Sanguin'),
(4, 8, '2021-12-09 22:56:05', 'Rouge'),
(5, 8, '2021-12-09 22:56:33', 'Vineux');

--
-- Déchargement des données de la table media
--
INSERT INTO media (id, mushroom_id, created_at, name, path, updated_at) VALUES
(16, 1, '2021-11-23 19:38:46', NULL, '619d434603bb8195867859.jpg', '2021-11-23 19:38:46'),
(17, 1, '2021-11-23 19:38:46', NULL, '619d434606189839000481.jpg', '2021-11-23 19:38:46'),
(18, 1, '2021-11-23 19:38:46', NULL, '619d434607188955443823.jpg', '2021-11-23 19:38:46'),
(19, 1, '2021-11-23 19:38:46', NULL, '619d434607e45955045587.jpg', '2021-11-23 19:38:46'),
(20, 1, '2021-11-23 19:38:46', NULL, '619d434608b17971041934.jpg', '2021-11-23 19:38:46'),
(21, 1, '2021-11-23 19:38:46', NULL, '619d43460982b650278964.jpg', '2021-11-23 19:38:46'),
(22, 1, '2021-11-23 19:38:46', NULL, '619d43460a50e440742534.jpg', '2021-11-23 19:38:46'),
(23, 2, '2021-11-23 19:38:46', NULL, '619d4d7752a57404259798.jpg', '2021-11-23 20:22:15'),
(24, 2, '2021-11-23 19:38:46', NULL, '619d4d775474b595885504.jpg', '2021-11-23 20:22:15'),
(25, 2, '2021-11-23 19:38:46', NULL, '619d4d7755185283227108.jpg', '2021-11-23 20:22:15'),
(26, 3, '2021-11-23 19:38:46', NULL, '619d4dc37bb40213226695.jpg', '2021-11-23 20:23:31'),
(27, 3, '2021-11-23 19:38:46', NULL, '619d4dc37d706207435784.jpg', '2021-11-23 20:23:31'),
(28, 3, '2021-11-23 19:38:46', NULL, '619d4dc37e1cc182187888.jpg', '2021-11-23 20:23:31'),
(29, 3, '2021-11-23 19:38:46', NULL, '619d4dc37ed69070908092.jpg', '2021-11-23 20:23:31'),
(30, 3, '2021-11-23 19:38:46', NULL, '619d4dc37f823639576016.jpg', '2021-11-23 20:23:31'),
(31, 3, '2021-11-23 19:38:46', NULL, '619d4dc380169149252145.jpg', '2021-11-23 20:23:31'),
(32, 3, '2021-11-23 19:38:46', NULL, '619d4dc380aa7105318088.jpg', '2021-11-23 20:23:31'),
(33, 3, '2021-11-23 19:38:46', NULL, '619d4dc381455247555696.jpg', '2021-11-23 20:23:31'),
(34, 4, '2021-11-23 19:38:46', NULL, '619d4ddd85baf949496533.jpg', '2021-11-23 20:23:57'),
(35, 5, '2021-11-23 19:38:46', NULL, '619d4e02c61ce922161025.jpg', '2021-11-23 20:24:34'),
(36, 5, '2021-11-23 19:38:46', NULL, '619d4e02c7d0c232258584.jpg', '2021-11-23 20:24:34'),
(37, 5, '2021-11-23 19:38:46', NULL, '619d4e02c88bb960793417.jpg', '2021-11-23 20:24:34'),
(38, 6, '2021-11-23 19:38:46', NULL, '619d4e22712c0235780704.jpg', '2021-11-23 20:25:06'),
(39, 6, '2021-11-23 19:38:46', NULL, '619d4e22731dd246193597.jpg', '2021-11-23 20:25:06'),
(40, 6, '2021-11-23 19:38:46', NULL, '619d4e2273d6a704307880.jpg', '2021-11-23 20:25:06'),
(41, 6, '2021-11-23 19:38:46', NULL, '619d4e22747ba321690021.jpg', '2021-11-23 20:25:06'),
(42, 7, '2021-11-23 19:38:46', NULL, '619d4e373ddb2515785160.jpg', '2021-11-23 20:25:27'),
(43, 7, '2021-11-23 19:38:46', NULL, '619d4e373fa27041614702.jpg', '2021-11-23 20:25:27'),
(44, 8, '2021-11-23 19:38:46', NULL, '619d4e8a898f2560464491.jpg', '2021-11-23 20:26:50'),
(45, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8b4ca163048289.jpg', '2021-11-23 20:26:50'),
(46, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8c143072836212.jpg', '2021-11-23 20:26:50'),
(47, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8cac3592464719.jpg', '2021-11-23 20:26:50'),
(48, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8d462151607044.jpg', '2021-11-23 20:26:50'),
(49, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8dde6775885416.jpg', '2021-11-23 20:26:50'),
(50, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8e5ff981349332.jpg', '2021-11-23 20:26:50'),
(51, 8, '2021-11-23 19:38:46', NULL, '619d4e8a8f2d4523124804.jpg', '2021-11-23 20:26:50'),
(52, 8, '2021-11-23 19:38:46', NULL, '619d4e8a900fe179131417.jpg', '2021-11-23 20:26:50'),
(53, 8, '2021-11-23 19:38:46', NULL, '619d4e8a90c64692599182.jpg', '2021-11-23 20:26:50'),
(54, 8, '2021-11-23 19:38:46', NULL, '619d4e8a9171a740662035.jpg', '2021-11-23 20:26:50'),
(55, 9, '2021-11-23 19:38:46', NULL, '619d4f4e73349598606479.jpg', '2021-11-23 20:30:06'),
(56, 9, '2021-11-23 19:38:46', NULL, '619d4f4e751b6389715275.jpg', '2021-11-23 20:30:06'),
(57, 9, '2021-11-23 19:38:46', NULL, '619d4f4e75bac282432581.jpg', '2021-11-23 20:30:06'),
(58, 9, '2021-11-23 19:38:46', NULL, '619d4f4e767b5560636267.jpg', '2021-11-23 20:30:06'),
(59, 10, '2021-11-23 19:38:46', NULL, '619d4f6f33e2a616390201.jpg', '2021-11-23 20:30:39'),
(60, 10, '2021-11-23 19:38:46', NULL, '619d4f6f35d69790520619.jpg', '2021-11-23 20:30:39'),
(61, 10, '2021-11-23 19:38:46', NULL, '619d4f6f3699e019147438.jpg', '2021-11-23 20:30:39'),
(62, 10, '2021-11-23 19:38:46', NULL, '619d4f6f3750a090000124.jpg', '2021-11-23 20:30:39'),
(63, 11, '2021-11-23 19:38:46', NULL, '619d4f99a6674463768339.jpg', '2021-11-23 20:31:21'),
(64, 11, '2021-11-23 19:38:46', NULL, '619d4f99a832c097884732.jpg', '2021-11-23 20:31:21'),
(65, 11, '2021-11-23 19:38:46', NULL, '619d4f99a9201425238936.jpg', '2021-11-23 20:31:21'),
(66, 12, '2021-11-23 19:38:46', NULL, '619d4fc31ab81271900535.jpg', '2021-11-23 20:32:03'),
(67, 12, '2021-11-23 19:38:46', NULL, '619d4fc31d0b5954026897.jpg', '2021-11-23 20:32:03'),
(68, 12, '2021-11-23 19:38:46', NULL, '619d4fc31de79231126909.jpg', '2021-11-23 20:32:03'),
(69, 12, '2021-11-23 19:38:46', NULL, '619d4fc31ec0a203452227.jpg', '2021-11-23 20:32:03'),
(70, 12, '2021-11-23 19:38:46', NULL, '619d4fc31f8f9007395448.jpg', '2021-11-23 20:32:03'),
(71, 12, '2021-11-23 19:38:46', NULL, '619d4fc320532260418207.jpg', '2021-11-23 20:32:03'),
(72, 13, '2021-11-23 19:38:46', NULL, '619d5037cd66b354565713.jpg', '2021-11-23 20:33:59'),
(73, 13, '2021-11-23 19:38:46', NULL, '619d5037cf817790021672.jpg', '2021-11-23 20:33:59'),
(74, 13, '2021-11-23 19:38:46', NULL, '619d5037d043c071629032.jpg', '2021-11-23 20:33:59'),
(75, 13, '2021-11-23 19:38:46', NULL, '619d5037d103a303095956.jpg', '2021-11-23 20:33:59'),
(76, 13, '2021-11-23 19:38:46', NULL, '619d5037d1d84240817573.jpg', '2021-11-23 20:33:59'),
(77, 14, '2021-11-23 19:38:46', NULL, '619d505a767bc414564921.jpg', '2021-11-23 20:34:34'),
(78, 14, '2021-11-23 19:38:46', NULL, '619d505a7830a018354058.jpg', '2021-11-23 20:34:34'),
(79, 14, '2021-11-23 19:38:46', NULL, '619d505a78f01205664713.jpg', '2021-11-23 20:34:34'),
(80, 15, '2021-11-23 19:38:46', NULL, '619d50b1805be574930290.jpg', '2021-11-23 20:36:01'),
(81, 15, '2021-11-23 19:38:46', NULL, '619d50b18273f903702961.jpg', '2021-11-23 20:36:01'),
(82, 15, '2021-11-23 19:38:46', NULL, '619d50b18345a845324303.jpg', '2021-11-23 20:36:01'),
(83, 15, '2021-11-23 19:38:46', NULL, '619d50b184370343501820.jpg', '2021-11-23 20:36:01'),
(84, 15, '2021-11-23 19:38:46', NULL, '619d50b1853cc745669665.jpg', '2021-11-23 20:36:01'),
(85, 15, '2021-11-23 19:38:46', NULL, '619d50b18642f556866965.jpg', '2021-11-23 20:36:01'),
(86, 15, '2021-11-23 19:38:46', NULL, '619d50b187554192798138.jpg', '2021-11-23 20:36:01'),
(87, 15, '2021-11-23 19:38:46', NULL, '619d50b1885b3636228797.jpg', '2021-11-23 20:36:01'),
(88, 15, '2021-11-23 19:38:46', NULL, '619d50b189266295018272.jpg', '2021-11-23 20:36:01'),
(89, 15, '2021-11-23 19:38:46', NULL, '619d50b189fe2044263032.jpg', '2021-11-23 20:36:01'),
(90, 15, '2021-11-23 19:38:46', NULL, '619d50b18b40a850351198.jpg', '2021-11-23 20:36:01'),
(91, 15, '2021-11-23 19:38:46', NULL, '619d50b18c441236289723.jpg', '2021-11-23 20:36:01'),
(92, 15, '2021-11-23 19:38:46', NULL, '619d50b18ceb7669893102.jpg', '2021-11-23 20:36:01'),
(93, 15, '2021-11-23 19:38:46', NULL, '619d50b18dac6625940075.jpg', '2021-11-23 20:36:01'),
(94, 16, '2021-11-23 19:38:46', NULL, '619d50deac44a218081052.jpg', '2021-11-23 20:36:46'),
(95, 16, '2021-11-23 19:38:46', NULL, '619d50deae070572911304.jpg', '2021-11-23 20:36:46'),
(96, 16, '2021-11-23 19:38:46', NULL, '619d50deaeaac710725885.jpg', '2021-11-23 20:36:46'),
(97, 16, '2021-11-23 19:38:46', NULL, '619d50deaf6d6872854927.jpg', '2021-11-23 20:36:46'),
(98, 16, '2021-11-23 19:38:46', NULL, '619d50deb00fc026214980.jpg', '2021-11-23 20:36:46'),
(99, 16, '2021-11-23 19:38:46', NULL, '619d50deb0c51654058594.jpg', '2021-11-23 20:36:46'),
(100, 17, '2021-11-23 19:38:46', NULL, '619d5109e8282012784925.jpg', '2021-11-23 20:37:29'),
(101, 17, '2021-11-23 19:38:46', NULL, '619d5109ea679285690688.jpg', '2021-11-23 20:37:29'),
(102, 17, '2021-11-23 19:38:46', NULL, '619d5109eb70a908510518.jpg', '2021-11-23 20:37:29'),
(103, 17, '2021-11-23 19:38:46', NULL, '619d5109ec1c8862013342.jpg', '2021-11-23 20:37:29'),
(104, 17, '2021-11-23 19:38:46', NULL, '619d5109ed0a7277379473.jpg', '2021-11-23 20:37:29'),
(105, 18, '2021-11-23 19:38:46', NULL, '619d514c324e2090876749.jpg', '2021-11-23 20:38:36'),
(106, 18, '2021-11-23 19:38:46', NULL, '619d514c3451e053889309.jpg', '2021-11-23 20:38:36'),
(107, 18, '2021-11-23 19:38:46', NULL, '619d514c35233462377069.jpg', '2021-11-23 20:38:36'),
(108, 18, '2021-11-23 19:38:46', NULL, '619d514c3607b712711565.jpg', '2021-11-23 20:38:36'),
(109, 18, '2021-11-23 19:38:46', NULL, '619d514c36df3091157114.jpg', '2021-11-23 20:38:36'),
(110, 18, '2021-11-23 19:38:46', NULL, '619d514c37f74365764693.jpg', '2021-11-23 20:38:36'),
(111, 18, '2021-11-23 19:38:46', NULL, '619d514c38c21269305310.jpg', '2021-11-23 20:38:36'),
(112, 19, '2021-11-23 19:38:46', NULL, '619d515e99583728042538.jpg', '2021-11-23 20:38:54'),
(113, 20, '2021-11-23 19:38:46', NULL, '619d51825f5a7893908965.jpg', '2021-11-23 20:39:30'),
(114, 20, '2021-11-23 19:38:46', NULL, '619d5182611d3612707393.jpg', '2021-11-23 20:39:30'),
(115, 20, '2021-11-23 19:38:46', NULL, '619d518261e8a028727209.jpg', '2021-11-23 20:39:30'),
(116, 20, '2021-11-23 19:38:46', NULL, '619d51826305d269066474.jpg', '2021-11-23 20:39:30'),
(117, 20, '2021-11-23 19:38:46', NULL, '619d518263e10175743939.jpg', '2021-11-23 20:39:30'),
(118, 20, '2021-11-23 19:38:46', NULL, '619d518264bea800394657.jpg', '2021-11-23 20:39:30'),
(119, 21, '2021-11-23 19:38:46', NULL, '619d51b4107bd413351131.jpg', '2021-11-23 20:40:20'),
(120, 21, '2021-11-23 19:38:46', NULL, '619d51b413456832959537.jpg', '2021-11-23 20:40:20'),
(121, 21, '2021-11-23 19:38:46', NULL, '619d51b4143c2724506866.jpg', '2021-11-23 20:40:20'),
(122, 22, '2021-11-23 19:38:46', NULL, '619d51cf27270644390513.jpg', '2021-11-23 20:40:47'),
(123, 22, '2021-11-23 19:38:46', NULL, '619d51cf2922e483140096.jpg', '2021-11-23 20:40:47'),
(124, 22, '2021-11-23 19:38:46', NULL, '619d51cf29e17753947442.jpg', '2021-11-23 20:40:47'),
(125, 24, '2021-11-23 19:38:46', NULL, '619d52138c19a109019589.jpg', '2021-11-23 20:41:55'),
(126, 24, '2021-11-23 19:38:46', NULL, '619d52138e087627993369.jpg', '2021-11-23 20:41:55'),
(127, 25, '2021-11-23 19:38:46', NULL, '619d523b63e43828436804.jpg', '2021-11-23 20:42:35'),
(128, 25, '2021-11-23 19:38:46', NULL, '619d523b6597f271192617.jpg', '2021-11-23 20:42:35'),
(129, 25, '2021-11-23 19:38:46', NULL, '619d523b663fb970666715.jpg', '2021-11-23 20:42:35'),
(130, 25, '2021-11-23 19:38:46', NULL, '619d523b66f73041773975.jpg', '2021-11-23 20:42:35'),
(131, 25, '2021-11-23 19:38:46', NULL, '619d523b67f53845054724.jpg', '2021-11-23 20:42:35'),
(132, 25, '2021-11-23 19:38:46', NULL, '619d523b68c4e541676732.jpg', '2021-11-23 20:42:35'),
(133, 26, '2021-11-23 19:38:46', NULL, '619d5250d563e931094437.jpg', '2021-11-23 20:42:56'),
(134, 26, '2021-11-23 19:38:46', NULL, '619d5250d71da397111802.jpg', '2021-11-23 20:42:56'),
(135, 27, '2021-11-23 19:38:46', NULL, '619d5271e08ab591725984.jpg', '2021-11-23 20:43:29'),
(136, 28, '2021-11-23 19:38:46', NULL, '619d5296d44a5724365090.jpg', '2021-11-23 20:44:06'),
(137, 28, '2021-11-23 19:38:46', NULL, '619d5296d65e4908755041.jpg', '2021-11-23 20:44:06'),
(138, 28, '2021-11-23 19:38:46', NULL, '619d5296d715f398478769.jpg', '2021-11-23 20:44:06'),
(139, 28, '2021-11-23 19:38:46', NULL, '619ebf981691a506289049.jpg', '2021-11-24 22:41:28');
Próbafeladat

Készíts egy (többrégtegű) alkalmazást. A kódot töltsd fel egy általunk is elérhető git
repository-ba, amelynek linkjét küld el nekünk. (Egy-egy kész feladatrész után commitolj, ne
csak a végeredményt töltsd fel.)
A kódolás során tartsd be az objektumorientált tervezés és programozás szabályait. Olyan
kódot szeretnénk kérni, ami megítélésed szerint jól reprezentálja a fejlesztői tudásodat.
Válassz olyan architektúrát, ami illik a feladathoz, ha úgy gondolod, használj nyugodtan 3rd
party library-ket.
Technológia
Alap esetben az általad választott, legjobban ismert technológiákat alkalmazd, ami jól
reprezentálja a tudásodat, valamint a fejlesztési ismereteidet mind adatbázis, mind backend
oldalon. Amennyiben az email kísérő szövegében szerepel konkrét technológia megjelölés,
úgy azt tekintsd mérvadónak.
Feladat leírás
Az alkalmazás tudjon egy adatbázisból dolgozva usereket és hozzájuk tartozó feed-ket
kezelni (Create, Read, Update, Delete) REST API-n keresztül. A feladat megoldásának csak
opcionális része a UI készítése, de az API tervezését a frontend igények alapján kell
elvégezni. A backend fölé tervezett felületen lehessen listázni a userek feedjeit. Egy
feednek különböző típusai lehetnek (text, image és videofeed).
Egy text feednek legyen egy felhasználója (aki publikálja azt), egy címe és egy leírása.
Az image feednek ugyan úgy legyen egy felhasználója, címe, leírása és extra adatként egy
kép tárolására is legyen alkalmas.
A video feed ugyanazokkal a tulajdonságokkal rendelkezik, mint egy image feed, de extra
adatként tudjon eltárolni egy video URL-t.
Továbbá a felhasználók lájkolhatják a feedeket és a feedek listázásánál a likeok számát is
meg kell jeleníteni.
A UI-hoz további vonalvezető, amit a backendnek támogatnia kell: A főképernyő egy feed
listából áll, ahol lehet felvenni/módosítani/törölni a feedeket. Az új feed létrehozása egy
modálban történjen, ahol a felhasználónak meg kell adnia a fentebb leírt adatokat, attól
függően, hogy milyen típusú feedet szeretne létrehozni. A feedek módosítása is egy
modálon keresztül történjen. A feed törléséhez legyen egy megerősítő modál.
Az adatszerkezet megtervezése és a REST API elkészítése a feladat központi része. A
beadandó feladat megoldása során használj migrációt és készíts seed adatokat.

Kiegészítő feladatok

● Swagger dokumentáció elkészítése

● Regisztráció/Login: user/password alapú bejelentkezés

● Jogosultság kezelés: minden felhasználó a saját feedjeit kezelheti csak
(módosítás / törlés)

● Logolás: minden műveletről készüljön log bejegyzés egy általad választott logolási
megoldással

● Hívjon meg egy külső RSS feed szolgáltatást és töltse be a saját feedek közé
listázáskor (https://apipheny.io/free-api/)

● Használj exception osztályokat a hibakezeléshez

● Használj error code-okat (errorok, validation hibák esetén is)

● A feed törlés soft delete-el működjön és minden nap éjfélkor törlődjön az
adatbázisból véglegesen

● Kommenteket is lehessen írni a feedekhez

● Dockerizáld a munkádat

Extra feladatok

● UI készítés

● Integrációs tesztek: tesztelési célt szolgáló alkalmazás felállítása a build részeként,
API tesztek

● Unit tesztek: az alkalmazás üzleti logikáját reprezentáló valamely komplexebb
osztályhoz

Legyél benne biztos

● Az alkalmazás lefut és buildelhető

● Minden követelménynek megfelel

● A kód szervezett, jól érthető

● Az alkalmazáshoz készíts egy rövid leírást, hogyan tudjuk lebuildelni, futtatni

Ha kérdésed van olyannal kapcsolatban, ami nincs specifikálva, nyugodtan adhatsz rá
saját kútfőből megoldást, hogy ne kelljen válaszra várnod.
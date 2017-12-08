truncate room;

INSERT INTO room (size, sea_viewable, free_minibar, description_en, description_ru)
VALUES (2, 1, 1, 'Our best room with the sea view, free minibar and large comfortable sofa ', 'Наша лучшая комната с видом на море, бесплатным минибаром и большим удобным диваном');

INSERT INTO room (size, sea_viewable, free_minibar, description_en, description_ru)
VALUES (1, 0, 1, 'Medium room wih free minibar', 'Средняя комната с бесплатными минибаром');

INSERT INTO room (sea_viewable, free_minibar, description_en, description_ru)
VALUES (0, 0, 0, 'Economy class for poor students', 'Эконом-класс для студентов');
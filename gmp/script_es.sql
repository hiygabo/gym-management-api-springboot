


-- SCRIPT DE INSERCIÓN - TABLAS NO TRANSACCIONALES
-- Sistema de Gestión de Gimnasio
--REQUISITOS PREVIOS: LEER DOCUMENTACION README_es.md--
--NOTA: SPRING BOOT CREARA LAS TABLAS AUTOMATICAMENTE AL CORRER EL SERVIDOR--


-- -----------------------------------------------
-- 1. CATEGORIA 
-- -----------------------------------------------
INSERT INTO categoria (id_categoria, nombre) VALUES
(1, 'Cardio'),
(2, 'Fuerza y Musculación'),
(3, 'Pesas Libres'),
(4, 'Funcional'),
(5, 'Flexibilidad y Estiramiento');


-- -----------------------------------------------
-- 2. EQUIPO 
-- -----------------------------------------------
INSERT INTO equipo (id_maquina, nombre, marca, estado, cantidad, id_categoria) VALUES
-- Cardio
(1,  'Cinta de Correr',        'Life Fitness',  'Activo', 6, 1),
(2,  'Bicicleta Estática',     'Technogym',     'Activo', 4, 1),
(3,  'Elíptica',               'Matrix',        'Activo', 4, 1),
(4,  'Remo Ergómetro',         'Concept2',      'Activo', 2, 1),
(5,  'Escaladora',             'StairMaster',   'Activo', 2, 1),
-- Fuerza y Musculación
(6,  'Prensa de Piernas',      'Technogym',     'Activo', 3, 2),
(7,  'Máquina de Pecho',       'Life Fitness',  'Activo', 2, 2),
(8,  'Jalón al Pecho',         'Body Solid',    'Activo', 2, 2),
(9,  'Curl de Bíceps',         'Matrix',        'Activo', 2, 2),
(10, 'Extensión de Cuádriceps','Technogym',     'Activo', 2, 2),
(11, 'Máquina de Espalda',     'Life Fitness',  'Inactivo',1, 2),
-- Pesas Libres
(12, 'Set de Mancuernas 5-50kg','York',         'Activo', 1, 3),
(13, 'Barra Olímpica',         'Rogue',         'Activo', 4, 3),
(14, 'Banco Plano',            'Body Solid',    'Activo', 3, 3),
(15, 'Banco Inclinado',        'Body Solid',    'Activo', 2, 3),
(16, 'Rack de Sentadillas',    'Rogue',         'Activo', 2, 3),
-- Funcional
(17, 'TRX / Suspensión',       'TRX',           'Activo', 6, 4),
(18, 'Kettlebells Set',        'Reebok',        'Activo', 2, 4),
(19, 'Cuerdas de Batalla',     'Generic',       'Activo', 2, 4),
(20, 'Caja Pliométrica',       'Rogue',         'Activo', 4, 4),
-- Flexibilidad
(21, 'Colchoneta de Yoga',     'Gaiam',         'Activo', 15, 5),
(22, 'Foam Roller',            'TriggerPoint',  'Activo', 10, 5),
(23, 'Pelota de Pilates',      'Generic',       'Activo', 8,  5);


-- -----------------------------------------------
-- 3. ENTRENADOR
-- -----------------------------------------------
INSERT INTO entrenador (id_entrenador, nombre, turno, estado) VALUES
(1, 'Carlos Mamani Quispe',    'Mañana',  'Activo'),
(2, 'Lucía Flores Condori',    'Mañana',  'Activo'),
(3, 'Roberto Chávez Poma',     'Tarde',   'Activo'),
(4, 'Valeria Huanca López',    'Tarde',   'Activo'),
(5, 'Diego Quispe Tarqui',     'Noche',   'Activo'),
(6, 'Paola Limachi Soto',      'Noche',   'Activo'),
(7, 'Andrés Colque Ramos',     'Mañana',  'Inactivo'),
(8, 'Natalia Apaza Villca',    'Tarde',   'Activo');


-- -----------------------------------------------
-- 4. PLANSUSCRIPCION
-- -----------------------------------------------
INSERT INTO plansuscripcion (id_plan, nombre, precio, descripcion) VALUES
(1, 'Plan Básico Mensual',
    150.00,
    'Acceso a sala de máquinas y pesas. Lunes a viernes, horario regular.'),

(2, 'Plan Estándar Mensual',
    220.00,
    'Acceso completo a todas las áreas. Incluye 2 clases grupales por semana.'),

(3, 'Plan Premium Mensual',
    350.00,
    'Acceso ilimitado 7 días. Clases grupales ilimitadas y 1 sesión con entrenador personal.'),

(4, 'Plan Básico Trimestral',
    400.00,
    'Acceso a sala de máquinas por 3 meses. Descuento del 11% vs mensual.'),

(5, 'Plan Estándar Trimestral',
    590.00,
    'Acceso completo por 3 meses con clases grupales. Descuento del 10%.'),

(6, 'Plan Premium Trimestral',
    950.00,
    'Acceso ilimitado 3 meses. Clases grupales y 3 sesiones con entrenador personal.'),

(7, 'Plan Anual Todo Incluido',
    2800.00,
    'Acceso ilimitado durante 12 meses. Clases grupales, evaluación física mensual y nutricionista.');


--PROCEDIMIENTO--
CREATE OR REPLACE PROCEDURE sp_desactivar_deudores()
LANGUAGE plpgsql
AS $$
DECLARE c1 CURSOR FOR 
	SELECT c.id_cliente FROM Cliente c
	JOIN Suscripcion s ON s.id_cliente = c.id_cliente
	WHERE s.fecha_fin < CURRENT_DATE
	AND c.estado = 'Activo';
	
	
	id_cliente BIGINT;
	contador INT := 0;
	
BEGIN
	OPEN c1;
	LOOP
		FETCH c1 INTO id_cliente;
		EXIT WHEN NOT FOUND;
		UPDATE cliente
		SET estado = 'Inactivo'
		WHERE id_cliente = id_cliente;
		contador := contador+1;
	END LOOP;
	CLOSE c1;
END;
$$;
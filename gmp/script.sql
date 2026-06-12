


-- INSERTION SCRIPT - TABLES NOT TRANSACTIONAL--
-- MANAGEMENT SYSTEM GYM--
--PREVIOUS REQUIREMENTS: READ DOCUMENTATION README.md--
--NOTE: SPRING BOOT WILL CREATE THE TABLES AUTOMATICALLY TO RUN SERVER--


-- -----------------------------------------------
-- 1. CATEGORY 
-- -----------------------------------------------
INSERT INTO categoria (id_categoria, nombre) VALUES
(1, 'Cardio'),
(2, 'Strength & Muscle Building'),
(3, 'Free Weights'),
(4, 'Functional'),
(5, 'Flexibility & Stretching');


-- -----------------------------------------------
-- 2. EQUIPMENT 
-- -----------------------------------------------
INSERT INTO equipo (id_maquina, nombre, marca, estado, cantidad, id_categoria) VALUES
-- Cardio
(1,  'Treadmill',             'Life Fitness',  'Active',   6, 1),
(2,  'Stationary Bike',       'Technogym',     'Active',   4, 1),
(3,  'Elliptical',            'Matrix',        'Active',   4, 1),
(4,  'Rowing Machine',        'Concept2',      'Active',   2, 1),
(5,  'Stair Climber',         'StairMaster',   'Active',   2, 1),
-- Strength & Muscle Building
(6,  'Leg Press',             'Technogym',     'Active',   3, 2),
(7,  'Chest Press Machine',   'Life Fitness',  'Active',   2, 2),
(8,  'Lat Pulldown',          'Body Solid',    'Active',   2, 2),
(9,  'Bicep Curl Machine',    'Matrix',        'Active',   2, 2),
(10, 'Leg Extension',         'Technogym',     'Active',   2, 2),
(11, 'Back Machine',          'Life Fitness',  'Inactive', 1, 2),
-- Free Weights
(12, 'Dumbbell Set 5-50kg',   'York',          'Active',   1, 3),
(13, 'Olympic Barbell',       'Rogue',         'Active',   4, 3),
(14, 'Flat Bench',            'Body Solid',    'Active',   3, 3),
(15, 'Incline Bench',         'Body Solid',    'Active',   2, 3),
(16, 'Squat Rack',            'Rogue',         'Active',   2, 3),
-- Functional
(17, 'TRX / Suspension',      'TRX',           'Active',   6, 4),
(18, 'Kettlebell Set',        'Reebok',        'Active',   2, 4),
(19, 'Battle Ropes',          'Generic',       'Active',   2, 4),
(20, 'Plyometric Box',        'Rogue',         'Active',   4, 4),
-- Flexibility
(21, 'Yoga Mat',              'Gaiam',         'Active',   15, 5),
(22, 'Foam Roller',           'TriggerPoint',  'Active',   10, 5),
(23, 'Pilates Ball',          'Generic',       'Active',   8,  5);


-- -----------------------------------------------
-- 3. TRAINER
-- -----------------------------------------------
INSERT INTO entrenador (id_entrenador, nombre, turno, estado) VALUES
(1, 'Carlos Mamani Quispe',    'Morning',   'Active'),
(2, 'Lucía Flores Condori',    'Morning',   'Active'),
(3, 'Roberto Chávez Poma',     'Afternoon', 'Active'),
(4, 'Valeria Huanca López',    'Afternoon', 'Active'),
(5, 'Diego Quispe Tarqui',     'Evening',   'Active'),
(6, 'Paola Limachi Soto',      'Evening',   'Active'),
(7, 'Andrés Colque Ramos',     'Morning',   'Inactive'),
(8, 'Natalia Apaza Villca',    'Afternoon', 'Active');


-- -----------------------------------------------
-- 4. SUBSCRIPTION PLAN
-- -----------------------------------------------
INSERT INTO plansuscripcion (id_plan, nombre, precio, descripcion) VALUES
(1, 'Basic Monthly Plan',
    150.00,
    'Access to machine and weight rooms. Monday to Friday, regular hours.'),

(2, 'Standard Monthly Plan',
    220.00,
    'Full access to all areas. Includes 2 group classes per week.'),

(3, 'Premium Monthly Plan',
    350.00,
    'Unlimited 7-day access. Unlimited group classes and 1 session with a personal trainer.'),

(4, 'Basic Quarterly Plan',
    400.00,
    'Machine room access for 3 months. 11% discount compared to monthly plan.'),

(5, 'Standard Quarterly Plan',
    590.00,
    'Full access for 3 months with group classes. 10% discount.'),

(6, 'Premium Quarterly Plan',
    950.00,
    'Unlimited access for 3 months. Group classes and 3 sessions with a personal trainer.'),

(7, 'All-Inclusive Annual Plan',
    2800.00,
    'Unlimited access for 12 months. Group classes, monthly physical evaluation, and nutritionist.');



--PROCEDURE--
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

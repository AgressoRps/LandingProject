<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bank" class="models.ProductsBank" type="models.ProductsBank"/>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Спорт-товары</title>

		<link rel="shortcut icon" href="resources/images/heart.ico" type="res/png">
        <link rel="stylesheet" type="text/css" href="css/main_css.css">
        <script type="text/javascript">
        	function showModalWin(id, name) {
			    var darkLayer = document.createElement('div');
			    var item = document.getElementById('item_product');
                var modalWin = document.getElementById('popupWin');
                var input = document.getElementById('hiddenInput');
                input.value = id;
			    item.textContent = "Приобрести " + name;
			    darkLayer.id = 'shadow'; 
			    document.body.appendChild(darkLayer);
			    modalWin.style.display = 'flex';
			    darkLayer.onclick = function () {
			    darkLayer.parentNode.removeChild(darkLayer); 
			    modalWin.style.display = 'none';
			    return false;
			    };
			}
        </script>
    </head>
    <body>
    	<header>
    		<div class="header_bar" id="main">
    			<div class="nav_container">
	    			<a href="#main">Главная</a>
	    			<a href="#advantages">Преимущества</a>
	    			<a href="#catalog">Каталог</a>
	    			<a href="#call_back">Обратная связь</a>
	    		</div>
	    		<div class="header_content">
	    			<div class="header_flex_column">
	    				<h1>
		    				Спортивный инвентарь
		    			</h1>
		    			<h2>
		    				По самым доступным ценам в Республике!
		    			</h2>
	    			</div>
	    			<div class="header_flex_column">
	    				<img src="resources/images/bodybuilding.png" alt="Гантель" >
	    			</div>
	    		</div>
	    		<div class="header_flex_button">
	    			<a href="#catalog"><button>
	    				Смотреть каталог
	    			</button></a>
	    		</div>
    		</div>
    	</header>
		<section>
			<div class="container_about_us">
				<div class="flex_title_about_us">
					<h2 id="advantages">Преимущества</h2>
				</div>
				<div class="flex_content_about">
					<div class="info_item">
						<img src="resources/images/medal.png" alt="Медаль">
						<h4>Признание клиентов</h4>
						<h5>Наши товары оценены<br>наивысшими оценками покупателей</h5>
					</div>
					<div class="info_item">
						<img src="resources/images/timer.png" alt="Медаль">
						<h4>Кратчайшие сроки доставки</h4>
						<h5>Ваш заказ будет доставлен<br>в самые краткие сроки</h5>
					</div>
					<div class="info_item">
						<img src="resources/images/list.png" alt="Медаль">
						<h4>Соотвествие стандартам</h4>
						<h5>Каждый элемент соответствует<br>международным стандартам качества</h5>
					</div>
					<div class="info_item">
						<img src="resources/images/drink.png" alt="Медаль">
						<h4>Мотивация</h4>
						<h5>Внешний вид мотивирует<br>на тренировки лучше любого видео</h5>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="container_motivation_for_buy">
				<h1>Миллионы человек по всему миру<br>в данный момент используют наше оборудование!</h1>
			</div>
		</section>
		<section>
			<div class="container_products">
				<!-- Наше модальное всплывающее окно -->
		        <div style="text-align: center" id="popupWin" class="modalwin">
		            <h2 id="item_product"></h2>
		            <form action="controller" method="post">
						<input type="hidden" id="hiddenInput" name="idProduct" value="">
		                <input type="text" name="clientName" placeholder="Ваше имя" required="true">
		                <input type="tel" name="clientPhoneNumber" placeholder="Ваш номер телефона" required="true">
		                <textarea placeholder="Ваше сообщение..." cols="30" rows="3"></textarea>
		                <input type="submit" value="Приобрести" id="submit">
		            </form>
		        </div>
				<div class="container_products_title">
					<h2 id="catalog">Каталог</h2>
				</div>
				<div class="container_products_content">
					<c:forEach var="item" items="${bank.products}">
						<div class="products_item">
							<img src="resources/images/${item.imageName}" alt="Товар">
							<h4>${item.name} ${item.weight} кг</h4>
							<h5>Стоимость: ${item.price}</h5>
							<form>
								<input type="button" value="Купить" onclick="showModalWin(${item.id}, '${item.name}')">
							</form>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
		<section>
			<div class="container_call_back">
				<h2 id="call_back">Обратная связь</h2>
				<form action="controller" method="post">
					<div class="form_call_back">
						<h4>Имя</h4>
						<input type="text" placeholder="Ваше имя" size="40" name="callMeName" required>
						<h4>Номер телефона</h4>
						<input type="tel" placeholder="Ваш номер телефона" size="40" name="callMePhone" required=>
						<h4>Сообщение</h4>
						<textarea placeholder="Ваше сообщение..." cols="40" name="callMeText" rows="3"></textarea>
						<button>Отправить</button>
					</div>
				</form>
			</div>
		</section>
		<footer>
			<div class="flex_footer">
				<div class="flex_column_copy">
					<h4>Все права защищены согласно законодательству ДНР.</h4>
					<h5>2018 &copy Старокожев Владислав</h5>
				</div>
				<div class="flex_column_nav">
					<a href="#main">Главная</a>
	    			<a href="#advantages">Преимущества</a>
	    			<a href="#catalog">Каталог</a>
	    			<a href="#call_back">Обратная связь</a>
				</div>
			</div>
		</footer>
    </body>
    <script src="http://code.jquery.com/jquery-latest.js" type="text/javascript"></script>
    <script src="js/anchor.js" type="text/javascript"></script>
</html>
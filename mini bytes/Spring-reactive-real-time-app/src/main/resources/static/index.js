


const stockBtn=document.getElementById("stock-btn")
stockBtn.addEventListener("click",e=>{
	
	// SSE api
	const eventSource=new EventSource("stock/123/price")
	eventSource.onmessage=message=>{
		document.getElementById("price").innerHTML=message.data
	}
	
})
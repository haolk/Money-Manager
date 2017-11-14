function printRocket(i, history){
  // post container frame
  $(".div-main").append('<div class="div-rocpost'+i+'-container"></div>')
  $(".div-rocpost"+i+"-container").css({
     "height": "120px",
     "width": "100%",
     "display": "flex",
     "flex-direction": "row",
     "border-radius": "10px",
     "margin-bottom": "25px",
     "border-style": "groove",
     "background-color": "rgba(100,100,100,0.1)" //Blue
  });

  // frame inside div: container-1
  $(".div-rocpost"+i+"-container").append('<div class="div-rocpost'+i+'-container-1"></div>')
  $(".div-rocpost"+i+"-container-1").css({
     "height": "100%",
     "width": "20%",
     "display": "flex",
     "flex-direction": "column"
    //  "background-color": "#bae1ee"
  });

  // frame inside div: container-1, subcontainer-1
  $(".div-rocpost"+i+"-container-1").append('<div class="div-rocpost'+i+'-container-1-1"></div>')
  $(".div-rocpost"+i+"-container-1-1").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "18px",
     "padding-left": "10px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#fae1ee"
  });

  // frame inside div: container-1, subcontainer-2
  $(".div-rocpost"+i+"-container-1").append('<div class="div-rocpost'+i+'-container-1-2"></div>')
  $(".div-rocpost"+i+"-container-1-2").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "padding-left": "10px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#00e1ee"
  });

  // frame inside div: container-1, subcontainer-3
  $(".div-rocpost"+i+"-container-1").append('<div class="div-rocpost'+i+'-container-1-3"></div>')
  $(".div-rocpost"+i+"-container-1-3").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "padding-left": "10px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#fa11ee"
  });

  // frame inside div: container-2
  $(".div-rocpost"+i+"-container").append('<div class="div-rocpost'+i+'-container-2"></div>')
  $(".div-rocpost"+i+"-container-2").css({
     "height": "100%",
     "width": "30%",
     "display": "flex",
     "flex-direction": "column"
    //  "background-color": "#bae132"
  });

  // frame inside div: container-2, subcontainer-1
  $(".div-rocpost"+i+"-container-2").append('<div class="div-rocpost'+i+'-container-2-1"></div>')
  $(".div-rocpost"+i+"-container-2-1").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#fae85e"
  });

  // frame inside div: container-2, subcontainer-2
  $(".div-rocpost"+i+"-container-2").append('<div class="div-rocpost'+i+'-container-2-2"></div>')
  $(".div-rocpost"+i+"-container-2-2").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#0ff1ee"
  });

  // frame inside div: container-2, subcontainer-3
  $(".div-rocpost"+i+"-container-2").append('<div class="div-rocpost'+i+'-container-2-3"></div>')
  $(".div-rocpost"+i+"-container-2-3").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#fa11bb"
  });

  // frame inside div: container-3
  $(".div-rocpost"+i+"-container").append('<div class="div-rocpost'+i+'-container-3"></div>')
  $(".div-rocpost"+i+"-container-3").css({
     "height": "100%",
     "width": "50%",
     "display": "flex",
     "flex-direction": "column"
    //  "background-color": "#b001ee"
  });

  // frame inside div: container-3, subcontainer-1
  $(".div-rocpost"+i+"-container-3").append('<div class="div-rocpost'+i+'-container-3-1"></div>')
  $(".div-rocpost"+i+"-container-3-1").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#fb775e"
  });

  // frame inside div: container-3, subcontainer-2
  $(".div-rocpost"+i+"-container-3").append('<div class="div-rocpost'+i+'-container-3-2"></div>')
  $(".div-rocpost"+i+"-container-3-2").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#01c55e"
  });

  // frame inside div: container-3, subcontainer-3
  $(".div-rocpost"+i+"-container-3").append('<div class="div-rocpost'+i+'-container-3-3"></div>')
  $(".div-rocpost"+i+"-container-3-3").css({
     "height": "40px",
     "width": "100%",
     "font-family": "Arial, Helvetica, sans-serif",
     "font-size": "14px",
     "vertical-align": "middle",
     "line-height": "40px" //the same as div height
    //  "background-color": "#fb305e"
  });

  $(".div-rocpost"+i+"-container-1-1").append("<b>"+history.filter+"</b>")
  $(".div-rocpost"+i+"-container-1-2").append("Amount : " + "<b>"+history.rocAmount+"</b>")
  $(".div-rocpost"+i+"-container-2-1").append(history.rocDate+"   "+history.rocTime)
  $(".div-rocpost"+i+"-container-2-2").append("Transaction Type : " + history.rocType)
  $(".div-rocpost"+i+"-container-3-1").append("Amount Nature : "+history.rocNature)
  $(".div-rocpost"+i+"-container-3-2").append("Bank Charge : " + history.rocBnkCharge)
  $(".div-rocpost"+i+"-container-3-3").append("Account Balance - Before : "+history.rocBalanceBefore+" Tk.   After : "+history.rocBalanceAfter+" Tk.")

}
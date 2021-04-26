using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Spofy.Models;
using Spofy.Services;
using Spofy.ViewModels;

namespace Spofy.Api.Controllers
{
    [Route("api/[controller]/[Action]")]
    [ApiController]
    public class AutorController : ControllerBase
    {
        #region ---------------- Dependencias  ---------------
        private readonly AutorServices _AutorService;

        public AutorController(AutorServices AutorServices)
        {
            this._AutorService = AutorServices;
        }
        #endregion

        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Authorize]
        public async Task<IActionResult> Get()
        {
            try
            {
                var lista = await _AutorService.GetAll();
                return Ok(lista);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        /// <summary>
        /// Crear nuevo autor
        /// </summary>
        /// <param name="AutorViewModel"></param>
        /// <returns></returns>
        [HttpPost]
        public async Task<IActionResult> Post(AutorViewModel AutorViewModel)
        {
            try
            {
                var Autor = new Autor
                {
                    Nombre = AutorViewModel.Nombre
                };

               await _AutorService.Create(Autor);
               return Ok("Autor creado correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        [HttpPut]
        public async Task<IActionResult> Update(Autor Autor)
        {
            try
            {
                await _AutorService.Update(Autor);
                return Ok("Autor actualizado correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(long AutorId)
        {
            try
            {
                await _AutorService.Delete(AutorId);
                return Ok("Autor Eliminado correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }


    }
}
